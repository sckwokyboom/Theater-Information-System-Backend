package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entitiies.Actor
import java.sql.ResultSet

class ActorRowMapper : RowMapper<Actor> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Actor {
        return Actor(
            rs.getInt("id"),
            rs.getString("first_name"),
            rs.getString("second_name"),
            rs.getString("patronymic"),
            rs.getInt("artist_id"),
            rs.getString("voice_type"),
            rs.getInt("weight"),
            rs.getInt("height"),
            rs.getString("hair_color"),
            rs.getString("eye_color"),
            rs.getString("skin_color"),
            rs.getInt("nationality_id"),
            rs.getString("nationality_name"),
        )
    }
}