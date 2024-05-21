package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entities.Subscription
import ru.nsu.fit.sckwo.model.entities.Ticket
import ru.nsu.fit.sckwo.model.mappers.SubscriptionRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class SubscriptionRepository(private val jdbcTemplate: JdbcTemplate) {
    fun getAllSubscriptions(): List<Subscription> {
        val sqlQuery = SqlQueryBuilder()
            .select("*")
            .from("subscriptions")
            .build()
        return jdbcTemplate.query(sqlQuery, SubscriptionRowMapper())
    }

    fun saveSubscription(subscription: Subscription): Int {
        val insert = SimpleJdbcInsert(jdbcTemplate)
            .withTableName("subscriptions")
            .usingGeneratedKeyColumns("id")

        val parameters = mapOf(
            "price" to subscription.price
        )

        return insert.executeAndReturnKey(parameters).toInt()
    }

    fun saveTickets(tickets: List<Ticket>) {
        val sql = """
            INSERT INTO tickets (performance_id, price, place_id, subscription_id, sale_date) 
            VALUES (?, ?, ?, ?, ?)
        """

        val batchArgs = tickets.map { ticket ->
            arrayOf(
                ticket.performanceId,
                ticket.price,
                ticket.placeId,
                ticket.subscriptionId ?: 0,
                java.sql.Date.valueOf(ticket.saleDate)
            )
        }

        jdbcTemplate.batchUpdate(sql, batchArgs)
    }

    fun createSubscriptionWithTicketsInDb(subPrice: Double, ticketData: String) {
        val sql = "CALL create_subscription_with_tickets(?, ?::JSONB)"
        jdbcTemplate.update(sql, subPrice, ticketData)
    }
}