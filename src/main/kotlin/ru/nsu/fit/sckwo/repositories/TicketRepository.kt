package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entities.Sum
import ru.nsu.fit.sckwo.model.entities.Ticket
import ru.nsu.fit.sckwo.model.mappers.SumRowMapper
import ru.nsu.fit.sckwo.model.mappers.TicketRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class TicketRepository(private val jdbcTemplate: JdbcTemplate) {
    fun getAllTickets(): List<Ticket> {
        val sqlQuery = SqlQueryBuilder()
            .select("*")
            .from("tickets")
            .build()
        return jdbcTemplate.query(sqlQuery, TicketRowMapper())
    }

    fun getTickets(
        performanceId: Int?,
        isPremiere: Boolean?,
        isUpcomingPerformances: Boolean?,
        dateOfStart: String?,
        dateOfEnd: String?,
        isPreSold: Boolean?,
    ): List<Ticket> {
        var sqlQueryBuilder = SqlQueryBuilder()
            .selectDistinct("tickets.id, plays.title play_title, tickets.price, halls.title hall_title, tickets.place_id, performances.id performance_id, tickets.subscription_id, tickets.sale_date")
            .from("tickets")
            .leftJoin("performances")
            .on("tickets.performance_id = performances.id")
            .leftJoin("halls")
            .on("performances.hall_id = halls.id")
            .leftJoin("places")
            .on("places.id = tickets.place_id")
            .leftJoin("plays")
            .on("performances.play_id = plays.id")

        if (performanceId != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("performances.id = $performanceId")
        }
        if (isPremiere != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("performances.is_premiere = $isPremiere")
        }
        if (isUpcomingPerformances != null) {
            if (isUpcomingPerformances) {
                sqlQueryBuilder = sqlQueryBuilder
                    .where("performances.start_time >= CURRENT_DATE")
            } else {
                sqlQueryBuilder = sqlQueryBuilder
                    .where("performances.end_time < CURRENT_DATE")
            }
        }
        if (dateOfStart != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("performances.start_time >= '$dateOfStart'")
        }
        if (dateOfEnd != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("performances.end_time <= '$dateOfEnd'")
        }
        if (isPreSold != null) {
            if (isPreSold) {
                sqlQueryBuilder = sqlQueryBuilder
                    .where("tickets.sale_date < performances.start_time")
            } else {
                sqlQueryBuilder = sqlQueryBuilder
                    .where("tickets.sale_date = performances.start_time")
            }
        }
        return jdbcTemplate.query(sqlQueryBuilder.build(), TicketRowMapper())
    }

    fun getSumFor(performanceId: Int?): List<Sum> {
        val sqlQueryBuilder = SqlQueryBuilder()
            .select("SUM(tickets.price)")
            .from("tickets")
        if (performanceId != null) {
            sqlQueryBuilder
                .where("tickets.performance_id = $performanceId")
        }

        return jdbcTemplate.query(sqlQueryBuilder.build(), SumRowMapper())
    }

    fun insert(ticket: Ticket) {
        val sqlQuery =
            "INSERT INTO tickets (performance_id, place_id, price, sale_date) VALUES(${ticket.performanceId}, ${ticket.placeId}, ${ticket.price}, CURRENT_DATE)"
        jdbcTemplate.update(sqlQuery)
    }

    fun existsById(id: Int): Boolean {
        val sqlQuery = SqlQueryBuilder()
            .select("COUNT(*)")
            .from("tickets")
            .where("id = $id")
            .build()
        val count = jdbcTemplate.queryForObject(sqlQuery, Int::class.java)
        return count != null && (count > 0)
    }

    fun deleteById(id: Int) {
        val sqlQuery = SqlQueryBuilder()
            .delete()
            .from("tickets")
            .where("id = $id")
            .build()
        jdbcTemplate.update(sqlQuery)
    }
}