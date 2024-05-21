package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entities.Actor
import ru.nsu.fit.sckwo.model.mappers.ActorRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class ActorRepository(private val jdbcTemplate: JdbcTemplate) {
    fun getAllActors(): List<Actor> {
        return getFilterActors(null, null, null, null, null, null, null, null, null, null, null, null, null, null)
    }

    fun getFilterActors(
        roleWeight: Int?,
        roleHeight: Int?,
        roleEyeColor: String?,
        roleSkinColor: String?,
        roleHairColor: String?,
        roleVoiceType: String?,
        roleGender: String?,
        roleAge: Int?,
        roleNationalityId: Int?,
        titleId: Int?,
        age: Int?,
        gender: String?,
        dateOfStartForTitle: String?,
        dateOfEndForTitle: String?,
    ): List<Actor> {
        var sqlQueryBuilder = SqlQueryBuilder()
            .selectDistinct("actors.id id, employees.first_name first_name, employees.second_name second_name, employees.patronymic patronymic, actors.artist_id artist_id, actors.voice voice_type, actors.weight, actors.height, actors.hair_color, actors.eye_color, actors.skin_color, nationalities.id nationality_id, nationalities.name nationality_name")
            .from("actors")
            .leftJoin("artists")
            .on("actors.artist_id = artists.id")
            .leftJoin("employees")
            .on("artists.employee_id = employees.id")
            .leftJoin("nationalities")
            .on("actors.nationality_id = nationalities.id")
            .leftJoin("actors_titles")
            .on("actors_titles.actor_id = actors.id")
            .leftJoin("titles")
            .on("titles.id = actors_titles.title_id")
            .leftJoin("competitions")
            .on("titles.competition_id = competitions.id")

        if (roleWeight != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("actors.weight = $roleWeight")
        }
        if (roleHeight != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("actors.height = $roleHeight")
        }
        if (roleEyeColor != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("actors.eye_color = $roleEyeColor")
        }
        if (roleSkinColor != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("actors.skin_color = $roleSkinColor")
        }
        if (roleHairColor != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("actors.hair_color = $roleHairColor")
        }
        if (roleVoiceType != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("actors.voice = $roleVoiceType")
        }
        if (roleGender != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("actors.gender = $roleGender")
        }
        if (roleAge != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("EXTRACT(YEAR FROM age(employees.date)) = $roleAge")
        }
        if (roleNationalityId != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("actors.nationality_id = $roleNationalityId")
        }
        if (titleId != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("actors_titles.title_id = $titleId")
        }
        if (age != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("EXTRACT(YEAR FROM age(employees.date)) = $age")
        }
        if (gender != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("actors.gender = $gender")
        }
        if (dateOfStartForTitle != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("competitions.date IS NOT NULL")
                .where("competitions.date >= '$dateOfStartForTitle'")
        }
        if (dateOfEndForTitle != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .where("competitions.date IS NOT NULL")
                .where("competitions.date <= '$dateOfEndForTitle'")
        }
        return jdbcTemplate.query(sqlQueryBuilder.build(), ActorRowMapper())
    }
}
