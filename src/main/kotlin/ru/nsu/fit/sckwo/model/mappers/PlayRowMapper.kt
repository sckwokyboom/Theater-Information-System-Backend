package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entities.Play
import java.sql.ResultSet

class PlayRowMapper : RowMapper<Play> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Play {
        return Play(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("genre_title"),
            rs.getInt("century")
        )
    }
}