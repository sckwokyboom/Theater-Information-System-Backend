package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entities.FreePlace
import ru.nsu.fit.sckwo.model.mappers.FreePlaceRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class FreePlaceRepository(private val jdbcTemplate: JdbcTemplate) {
    // This is for just places in halls, not free
//    fun getAllFreePlaces(): List<FreePlace> {
//        val sqlQuery = SqlQueryBuilder()
//            .select("places.id, halls.title, places.price_coefficient")
//            .from("places")
//            .leftJoin("halls")
//            .on("places.hall_id = halls.id")
//            .build()
//        return jdbcTemplate.query(sqlQuery, FreePlaceRowMapper())
//    }

    fun getPlaces(
        performanceId: Int?,
        isPremiere: Boolean?,
        isUpcomingPerformances: Boolean?,
    ): List<FreePlace> {
        var sqlQueryBuilder = SqlQueryBuilder()
            .selectDistinct("places.id, halls.title hall_title, places.price_coefficient price_coefficient, performances.id performance_id")
            .from("performances")
            .leftJoin("places")
            .on("places.hall_id = performances.hall_id")
            .leftJoin("tickets")
            .on("tickets.place_id = places.id AND tickets.performance_id = performances.id")
            .leftJoin("halls")
            .on("places.hall_id = halls.id")
            .where("tickets.id IS NULL")

        if (isUpcomingPerformances != null) {
            if (isUpcomingPerformances) {
                sqlQueryBuilder = sqlQueryBuilder
                    .where("performances.start_time >= CURRENT_DATE")
            } else {
                sqlQueryBuilder = sqlQueryBuilder
                    .where("performances.end_time < CURRENT_DATE")
            }
        }

        if (isPremiere != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("performances.is_premiere = $isPremiere")
        }

        if (performanceId != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("performances.id = $performanceId")
        }

        println(performanceId)
        println(isPremiere)
        println(isUpcomingPerformances)
        return jdbcTemplate.query(sqlQueryBuilder.build(), FreePlaceRowMapper())
    }
}