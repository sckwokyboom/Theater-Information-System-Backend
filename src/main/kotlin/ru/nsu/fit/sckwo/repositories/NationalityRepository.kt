package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entities.Nationality
import ru.nsu.fit.sckwo.model.mappers.NationalityRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class NationalityRepository(private val jdbcTemplate: JdbcTemplate) {
    fun getAllNationalities(): List<Nationality> {
        val sqlQuery = SqlQueryBuilder()
            .select("id, name")
            .from("nationalities")
            .build()
        return jdbcTemplate.query(sqlQuery, NationalityRowMapper())
    }
}
