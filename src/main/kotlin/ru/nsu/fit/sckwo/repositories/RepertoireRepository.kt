package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entities.Repertoire
import ru.nsu.fit.sckwo.model.mappers.RepertoireRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class RepertoireRepository(private val jdbcTemplate: JdbcTemplate) {
    fun getAllRepertoires(): List<Repertoire> {
        val sqlQuery = SqlQueryBuilder()
            .select("*")
            .from("repertoires")
            .build()
        return jdbcTemplate.query(sqlQuery, RepertoireRowMapper())
    }
}
