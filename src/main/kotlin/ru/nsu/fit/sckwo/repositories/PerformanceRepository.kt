package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entities.Performance
import ru.nsu.fit.sckwo.model.mappers.PerformanceRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class PerformanceRepository(private val jdbcTemplate: JdbcTemplate) {
    fun getPerformances(
        repertoireId: Int?,
        isPremiere: Boolean?,
        genreId: Int?,
        dateOfStart: String?,
        dateOfEnd: String?,
        authorId: Int?,
        authorCountryId: Int?,
        centuryOfPlayWriting: Int?,
        isUpcoming: Boolean?,
    ): List<Performance> {
        var sqlQueryBuilder = SqlQueryBuilder()
            .select(
                "performances.id, " +
                        "plays.title play_title, " +
                        "genres.title play_genre, " +
                        "plays.century, " +
                        "authors.first_name author_first_name, " +
                        "authors.second_name author_second_name, " +
                        "performances.start_time, " +
                        "performances.end_time, " +
                        "halls.title hall_title, " +
                        "performances.age_category, " +
                        "performances.base_price, " +
                        "performances.is_premiere"
            )
            .from("performances")
            .leftJoin("plays")
            .on("plays.id = performances.play_id")
            .leftJoin("halls")
            .on("performances.hall_id = halls.id")
            .leftJoin("authors_plays")
            .on("performances.play_id = authors_plays.play_id")
            .leftJoin("authors")
            .on("authors.id = authors_plays.author_id")
            .leftJoin("genres")
            .on("plays.genre_id = genres.id")

        if (genreId != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("plays.genre_id = $genreId")
        }
        if (repertoireId != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .rightJoin("repertoires_performances")
                .on("repertoires_performances.performance_id = performances.id")
                .where("repertoires_performances.repertoire_id = $repertoireId")
        }
        if (isPremiere != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("is_premiere = $isPremiere")
        }
        if (dateOfStart != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("performances.start_time >= \'$dateOfStart\'")
        }
        if (dateOfEnd != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("performances.end_time <= \'$dateOfEnd\'")
        }
        if (authorId != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("authors_plays.author_id = $authorId")
        }
        if (centuryOfPlayWriting != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("plays.century = $centuryOfPlayWriting")
        }
        if (authorCountryId != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("authors.country_of_origin_id = $authorCountryId")
        }
        if (isUpcoming != null) {
            if (isUpcoming) {
                sqlQueryBuilder = sqlQueryBuilder.where("performances.start_time >= CURRENT_DATE")
            }
        }
        return jdbcTemplate.query(sqlQueryBuilder.build(), PerformanceRowMapper())
    }

}
