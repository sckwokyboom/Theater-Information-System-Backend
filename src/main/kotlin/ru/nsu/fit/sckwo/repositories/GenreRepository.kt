package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entitiies.Genre
import ru.nsu.fit.sckwo.model.mappers.GenreRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class GenreRepository(private val jdbcTemplate: JdbcTemplate) {
    fun getAllGenres(): List<Genre> {
        val sqlQuery = SqlQueryBuilder()
            .select("id, title")
            .from("genres")
            .build()
        return jdbcTemplate.query(sqlQuery, GenreRowMapper())
    }
}
