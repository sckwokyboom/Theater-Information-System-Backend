package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entitiies.Country
import ru.nsu.fit.sckwo.model.mappers.CountryRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class CountryRepository(private val jdbcTemplate: JdbcTemplate) {
    fun getAllCountries(): List<Country> {
        val sqlQuery = SqlQueryBuilder()
            .select("id, name")
            .from("countries")
            .build()
        return jdbcTemplate.query(sqlQuery, CountryRowMapper())
    }
}
