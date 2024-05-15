package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entitiies.Ticket
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
}