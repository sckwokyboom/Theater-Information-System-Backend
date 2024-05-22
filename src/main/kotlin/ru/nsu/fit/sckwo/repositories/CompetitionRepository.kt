package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entities.Competition
import ru.nsu.fit.sckwo.model.mappers.CompetitionRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class CompetitionRepository(private val jdbcTemplate: JdbcTemplate) {
    fun getAllCompetitions(): List<Competition> {
        val sqlQuery = SqlQueryBuilder()
            .select("*")
            .from("competitions")
            .build()
        return jdbcTemplate.query(sqlQuery, CompetitionRowMapper())
    }
}
