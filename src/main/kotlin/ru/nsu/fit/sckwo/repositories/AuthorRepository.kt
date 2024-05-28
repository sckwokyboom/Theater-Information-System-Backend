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
        dateOfStartPerformanceAuthorsPlays: String?,
        dateOfEndPerformanceAuthorsPlays: String?,
        performanceId: Int?,
    ): List<Author> {
        var sqlQueryBuilder = SqlQueryBuilder()
            .selectDistinct("authors.id, first_name, second_name, patronymic, date_of_birth, date_of_death, countries.name country_of_origin_name")
            .from("authors")
            .leftJoin("countries")
            .on("authors.country_of_origin_id = countries.id")
            .leftJoin("authors_plays")
            .on("authors_plays.author_id = authors.id")
            .leftJoin("plays")
            .on("plays.id = authors_plays.play_id")

//        if (wasPerformed != null) {
//            sqlQueryBuilder = sqlQueryBuilder
//                .leftJoin("authors_plays")
//                .on("authors_plays.author_id = authors.id")
//                .leftJoin("plays")
//                .on("plays.id = authors_plays.play_id")
//                .leftJoin("performances")
//                .on("performances.play_id = plays.id")
//            sqlQueryBuilder = if (wasPerformed) {
//                sqlQueryBuilder
//                    .where("performances.play_id IS NOT NULL")
//            } else {
//                sqlQueryBuilder
//                    .where("performances.play_id IS NULL")
//            }
//        }
        if (wasPerformed != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .leftJoin("performances p4")
                .on("p4.play_id = plays.id")
                .where(if (wasPerformed) "p4.play_id IS NOT NULL" else "p4.play_id IS NULL")
        }

        if (centuryOfLiving != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("EXTRACT(CENTURY FROM authors.date_of_birth) = $centuryOfLiving OR EXTRACT(CENTURY FROM authors.date_of_death) = $centuryOfLiving OR ((EXTRACT(CENTURY FROM authors.date_of_birth) = 20 OR EXTRACT(CENTURY FROM authors.date_of_birth) = 21 OR EXTRACT(CENTURY FROM authors.date_of_birth) = 19) AND authors.date_of_death IS NULL)")
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
        }
        if (dateOfStartPerformanceAuthorsPlays != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .rightJoin("performances p3")
                .on("plays.id = p3.play_id")
                .where("p3.date <= $dateOfStartPerformanceAuthorsPlays")
        }

        if (dateOfEndPerformanceAuthorsPlays != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .rightJoin("performances p2")
                .on("plays.id = p2.play_id")
                .where("p2.date >= $dateOfEndPerformanceAuthorsPlays")
        }

        if (performanceId != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .rightJoin("performances p1")
                .on("plays.id = p1.play_id")
                .where("p1.id = $performanceId")
        }

        return jdbcTemplate.query(sqlQueryBuilder.build(), AuthorRowMapper()).distinctBy { it.id }
    }
}
