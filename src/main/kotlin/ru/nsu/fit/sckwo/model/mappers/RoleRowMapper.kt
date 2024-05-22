package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entities.Role
import java.sql.ResultSet

class RoleRowMapper : RowMapper<Role> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Role {
        var weight: Int? = rs.getInt("weight")
        if (rs.wasNull()) {
            weight = null
        }

        var height: Int? = rs.getInt("height")
        if (rs.wasNull()) {
            height = null
        }

        var age: Int? = rs.getInt("age")
        if (rs.wasNull()) {
            age = null
        }

        var nationalityId: Int? = rs.getInt("nationality_id")
        if (rs.wasNull()) {
            nationalityId = null
        }

        return Role(
            rs.getInt("id"),
            rs.getString("name"),
            weight,
            height,
            rs.getString("eye_color"),
            rs.getString("skin_color"),
            rs.getString("hair_color"),
            rs.getString("voice_type"),
            rs.getString("gender"),
            age,
            rs.getInt("play_id"),
            rs.getString("description"),
            nationalityId,
            rs.getString("nationality_name"),
        )
    }
}