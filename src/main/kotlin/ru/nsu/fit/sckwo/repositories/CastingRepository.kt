package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entities.Casting
import ru.nsu.fit.sckwo.model.mappers.CastingRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class CastingRepository(private val jdbcTemplate: JdbcTemplate) {
    fun getAllCastings(): List<Casting> {
        val sqlQuery = SqlQueryBuilder()
            .select("castings.actor_id, castings.performance_id, casting.double_id, castings.role_id")
            .from("castings")
            .build()
        return jdbcTemplate.query(sqlQuery, CastingRowMapper())
    }

    fun getCastings(
        actorId: Int?,
        dateOfStart: String?,
        dateOfEnd: String?,
        playGenreId: Int?,
        productionDirectorId: Int?,
        ageCategory: String?,
    ): List<Casting> {
        var sqlQueryBuilder = SqlQueryBuilder()
            .select("castings.actor_id actor_id, employees.first_name first_name, employees.second_name second_name, plays.title title, performances.date date, performances.id performance_id, castings.double_id double_id, roles.id role_id, roles.name role_name, roles.description role_description")
            .from("castings")
            .leftJoin("actors")
            .on("castings.actor_id = actors.id")
            .leftJoin("artists")
            .on("actors.artist_id = artists.id")
            .leftJoin("employees")
            .on("artists.employee_id = employees.id")
            .leftJoin("performances")
            .on("castings.performance_id = performances.id")
            .leftJoin("plays")
            .on("performances.play_id = plays.id")
            .leftJoin("genres")
            .on("plays.genre_id = genres.id")
            .leftJoin("roles")
            .on("castings.role_id = roles.id")
            .leftJoin("production_directors_performances")
            .on("production_directors_performances.performance_id = performances.id")
        if (actorId != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("actors.id = $actorId")
        }
        if (dateOfStart != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("performances.date >= $dateOfStart")
        }
        if (dateOfEnd != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("performances.date >= $dateOfEnd")
        }
        if (playGenreId != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("genres.id = $playGenreId")
        }
        if (productionDirectorId != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("production_directors_performances.production_director_id = $productionDirectorId")
        }
        if (ageCategory != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("performances.age_category = $ageCategory")
        }
        return jdbcTemplate.query(sqlQueryBuilder.build(), CastingRowMapper())
    }
}
