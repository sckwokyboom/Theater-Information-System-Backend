package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entities.Author
import ru.nsu.fit.sckwo.model.mappers.AuthorRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class AuthorRepository(private val jdbcTemplate: JdbcTemplate) {
    fun getAllAuthors(): List<Author> {
        val sqlQuery = SqlQueryBuilder()
            .select("authors.id, first_name, second_name, patronymic, date_of_birth, date_of_death, countries.name country_of_origin_name")
            .from("authors")
            .leftJoin("countries")
            .on("authors.country_of_origin_id = countries.id")
            .build()
        return jdbcTemplate.query(sqlQuery, AuthorRowMapper())
    }

    fun getFilterAuthors(
        wasPerformed: Boolean?,
        centuryOfLiving: Int?,
        countryOfOriginId: Int?,
        genreId: Int?,
        dateOfStartPerformanceAuthorsPlays: Int?,
        dateOfEndPerformanceAuthorsPlays: Int?,
    ): List<Author> {
        var sqlQueryBuilder = SqlQueryBuilder()
            .selectDistinct("authors.id, first_name, second_name, patronymic, date_of_birth, date_of_death, countries.name country_of_origin_name")
            .from("authors")
            .leftJoin("countries")
            .on("authors.country_of_origin_id = countries.id")

        if (wasPerformed != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .leftJoin("authors_plays")
                .on("authors_plays.author_id = authors.id")
                .leftJoin("plays")
                .on("plays.id = authors_plays.play_id")
                .leftJoin("performances")
                .on("performances.play_id = plays.id")
            sqlQueryBuilder = if (wasPerformed) {
                sqlQueryBuilder
                    .where("performances.play_id IS NOT NULL")
            } else {
                sqlQueryBuilder
                    .where("performances.play_id IS NULL")
            }
        }
        if (centuryOfLiving != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("century_of_living = $centuryOfLiving")
        }
        if (countryOfOriginId != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("countries.id = $countryOfOriginId")
        }
        if (genreId != null) {
            val subQuery = SqlQueryBuilder()
                .selectDistinct("authors.id")
                .from("authors")
                .leftJoin("authors_plays")
                .on("authors_plays.author_id = authors.id")
                .leftJoin("plays")
                .on("plays.id = authors_plays.play_id")
                .where("plays.genre_id = $genreId")
                .build()
            sqlQueryBuilder = sqlQueryBuilder
                .where("authors.id IN ($subQuery)")
//                .leftJoin("authors_plays")
//                .on("authors_plays.author_id = authors.id")
//                .leftJoin("plays")
//                .on("plays.id = authors_plays.play_id")
//                .where("plays.genre_id = $genreId")
        }
        if (dateOfStartPerformanceAuthorsPlays != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .leftJoin("authors_plays")
                .on("authors_plays.author_id = authors.id")
                .leftJoin("plays")
                .on("plays.id = authors_plays.play_id")
                .rightJoin("performances")
                .on("plays.id = performances.play_id")
                .where("performances.date <= $dateOfStartPerformanceAuthorsPlays")
        }

        if (dateOfEndPerformanceAuthorsPlays != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .leftJoin("authors_plays")
                .on("authors_plays.author_id = author.id")
                .leftJoin("plays")
                .on("plays.id = authors_plays.play_id")
                .rightJoin("performances")
                .on("plays.id = performances.play_id")
                .where("performances.date >= $dateOfEndPerformanceAuthorsPlays")
        }

        return jdbcTemplate.query(sqlQueryBuilder.build(), AuthorRowMapper())
    }
}
