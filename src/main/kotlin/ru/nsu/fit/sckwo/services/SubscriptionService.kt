package ru.nsu.fit.sckwo.services

import org.springframework.jdbc.core.CallableStatementCallback
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.nsu.fit.sckwo.model.entities.BuySubscriptionRequest
import ru.nsu.fit.sckwo.repositories.SubscriptionRepository
import java.sql.SQLException


@Service
class SubscriptionService(
    private val subscriptionRepository: SubscriptionRepository,
    private val jdbcTemplate: JdbcTemplate,
) {


//    @Transactional
//    fun createSubscriptionWithTickets(subscription: Subscription, tickets: List<Ticket>): Subscription {
//        val subscriptionId = subscriptionRepository.saveSubscription(subscription)
//        val ticketsWithSubscription = tickets.map { it.copy(subscriptionId = subscriptionId) }
//        subscriptionRepository.saveTickets(ticketsWithSubscription)
//        return subscription.copy(id = subscriptionId)
//    }

    @Transactional
    fun deleteSubscriptionWithTickets(subscriptionId: Int) {
        // Удаление связанных билетов
        val deleteTicketsSql = "DELETE FROM tickets WHERE subscription_id = ?"
        jdbcTemplate.update(deleteTicketsSql, subscriptionId)

        // Удаление подписки
        val deleteSubscriptionSql = "DELETE FROM subscriptions WHERE id = ?"
        jdbcTemplate.update(deleteSubscriptionSql, subscriptionId)
    }

    @Transactional
    @Throws(SQLException::class)
    fun createSubscriptionWithTickets(performanceIds: List<Int>, placeIds: List<Int>) {
        require(performanceIds.size == placeIds.size) { "The length of performanceIds and placeIds must be the same" }

        val namedParameterJdbcTemplate = NamedParameterJdbcTemplate(jdbcTemplate)

        // Check for overlap
        val checkOverlapSql = """
        SELECT 1 FROM performances p1, performances p2 
        WHERE p1.id = :performanceId 
          AND p1.id != p2.id 
          AND p1.start_time <= p2.end_time 
          AND p1.end_time >= p2.start_time
    """
        for (performanceId in performanceIds) {
            val params = mapOf("performanceId" to performanceId)
            val result = namedParameterJdbcTemplate.query(checkOverlapSql, params) { rs, _ ->
                if (rs.next()) {
                    throw SQLException("Performances overlap in time")
                }
            }
        }

        // Calculate total price
        val priceSql = """
        SELECT p.base_price * pl.price_coefficient 
        FROM performances p 
        JOIN places pl ON p.id = :performanceId AND pl.id = :placeId
    """
        var totalPrice = 0.0
        val prices = DoubleArray(performanceIds.size)
        for (i in performanceIds.indices) {
            val params = mapOf("performanceId" to performanceIds[i], "placeId" to placeIds[i])
            val price = namedParameterJdbcTemplate.queryForObject(priceSql, params, Double::class.java)
            if (price != null) {
                prices[i] = price
                totalPrice += price
            } else {
                throw SQLException("Price calculation failed for performanceId: ${performanceIds[i]} and placeId: ${placeIds[i]}")
            }
        }

        // Insert into subscriptions
        val insertSubscriptionSql = "INSERT INTO subscriptions (price) VALUES (:price) RETURNING id"
        val params = mapOf("price" to totalPrice)
        val subscriptionId = namedParameterJdbcTemplate.queryForObject(insertSubscriptionSql, params, Int::class.java)
            ?: throw SQLException("Failed to insert subscription")

        // Insert into tickets
        val insertTicketSql = """
        INSERT INTO tickets (performance_id, price, place_id, subscription_id, sale_date) 
        VALUES (:performanceId, :price, :placeId, :subscriptionId, CURRENT_DATE)
    """
        for (i in performanceIds.indices) {
            val ticketParams = mapOf(
                "performanceId" to performanceIds[i],
                "price" to prices[i],
                "placeId" to placeIds[i],
                "subscriptionId" to subscriptionId
            )
            namedParameterJdbcTemplate.update(insertTicketSql, ticketParams)
        }
    }


    fun createSubscriptionWithTicketsInDb(subPrice: Double, ticketData: String) {
        subscriptionRepository.createSubscriptionWithTicketsInDb(subPrice, ticketData)
    }

    fun buySubscription(request: BuySubscriptionRequest) {
        val sql = "{ call create_subscription_with_tickets(?, ?) }"


        jdbcTemplate.execute(sql, CallableStatementCallback { cs ->
            cs.setArray(1, cs.connection.createArrayOf("INTEGER", request.performanceIds.toTypedArray()))
            cs.setArray(2, cs.connection.createArrayOf("INTEGER", request.placeIds.toTypedArray()))
            cs.execute()
        })
    }
}
