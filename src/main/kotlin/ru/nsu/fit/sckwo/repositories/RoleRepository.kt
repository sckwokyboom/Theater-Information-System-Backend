package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entitiies.Role
import ru.nsu.fit.sckwo.model.mappers.RoleRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class RoleRepository(private val jdbcTemplate: JdbcTemplate) {
    fun getAllRoles(): List<Role> {
        val sqlQuery = SqlQueryBuilder()
            .select("roles.id, roles.weight, roles.height, roles.eye_color, roles.skin_color, roles.hair_color, roles.voice_type, roles.gender, roles.age, roles.play_id, roles.description, roles.nationality_id")
            .from("roles")
            .build()
        return jdbcTemplate.query(sqlQuery, RoleRowMapper())
    }
}