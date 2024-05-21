package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entities.Casting
import java.sql.ResultSet

class CastingRowMapper : RowMapper<Casting> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Casting {
        return Casting(
            rs.getInt("actor_id"),
            rs.getString("first_name"),
            rs.getString("second_name"),
            rs.getString("title"),
            rs.getDate("date").toLocalDate(),
            rs.getInt("performance_id"),
            rs.getInt("double_id"),
            rs.getInt("role_id"),
            rs.getString("role_name"),
            rs.getString("role_description"),
        )
    }
}