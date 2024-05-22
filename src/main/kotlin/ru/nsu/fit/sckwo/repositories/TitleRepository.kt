package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entities.Title
import ru.nsu.fit.sckwo.model.mappers.TitleRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class TitleRepository(private val jdbcTemplate: JdbcTemplate) {
    fun getAllTitles(): List<Title> {
        val sqlQuery = SqlQueryBuilder()
            .select("titles.id, titles.name, titles.competition_id, competitions.name competition_name")
            .from("titles")
            .leftJoin("competitions")
            .on("competitions.id = titles.competition_id")
            .build()
        return jdbcTemplate.query(sqlQuery, TitleRowMapper())
    }

}