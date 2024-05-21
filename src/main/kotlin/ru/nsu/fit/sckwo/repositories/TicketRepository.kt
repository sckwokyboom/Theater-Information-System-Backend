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
        isPremiere: String?,
        isUpcomingPerformances: String?,
        dateOfStart: String?,
        dateOfEnd: String?,
        isPreSold: String?,
    ): List<Ticket> {
        var sqlQueryBuilder = SqlQueryBuilder()
            .select("tickets.id, plays.title, tickets.price, halls.title, places.id")
            .from("tickets")
            .leftJoin("performances")
            .on("tickets.performance_id = performances.id")
            .leftJoin("halls")
            .on("performance.hall_id = halls.id")
            .leftJoin("places")
            .on("places.hall_id = halls.id")
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
            sqlQueryBuilder = sqlQueryBuilder
                .where("performances.date >= CURRENT_DATE")
        }
        if (dateOfStart != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("performances.date >= $dateOfStart")
        }
        if (dateOfEnd != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("performances.date <= $dateOfEnd")
        }
        if (isPreSold != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("tickets.sale_date < performances.date")
        }
        return jdbcTemplate.query(sqlQueryBuilder.build(), TicketRowMapper())
    }

    fun getSumFor(performanceId: Int): List<Sum> {
        val sqlQuery = SqlQueryBuilder()
            .select("SUM(tickets.price")
            .from("tickets")
            .leftJoin("performances")
            .on("tickets.performance_id = performances.id")
            .leftJoin("halls")
            .on("performance.hall_id = halls.id")
            .leftJoin("places")
            .on("places.hall_id = halls.id")
            .leftJoin("plays")
            .on("performances.play_id = plays.id")
            .where("performances.id = $performanceId")
            .build()
        return jdbcTemplate.query(sqlQuery, SumRowMapper())
    }
}