package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entitiies.Genre
import java.sql.ResultSet

class GenreRowMapper : RowMapper<Genre> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Genre {
        return Genre(
            rs.getInt("id"),
            rs.getString("title"),
        )
    }
}