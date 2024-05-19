package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entitiies.Role
import java.sql.ResultSet

class RoleRowMapper : RowMapper<Role> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Role {
        return Role(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("weight"),
            rs.getInt("height"),
            rs.getString("eye_color"),
            rs.getString("skin_color"),
            rs.getString("hair_color"),
            rs.getString("voice_type"),
            rs.getString("gender"),
            rs.getInt("age"),
            rs.getInt("play_id"),
            rs.getString("description"),
            rs.getInt("nationality_id"),
        )
    }
}