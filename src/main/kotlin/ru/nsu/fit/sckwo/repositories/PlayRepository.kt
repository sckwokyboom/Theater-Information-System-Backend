package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entitiies.Play
import ru.nsu.fit.sckwo.model.mappers.PlayRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class PlayRepository(private val jdbcTemplate: JdbcTemplate) {
    fun getAllPlays(): List<Play> {
        val sqlQuery = SqlQueryBuilder()
            .select("plays.id, plays.title, genres.title genre_title, plays.century")
            .from("plays")
            .leftJoin("genres")
            .on("plays.genre_id = genres.id")
            .build()
        return jdbcTemplate.query(sqlQuery, PlayRowMapper())
    }
}