package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entitiies.Performance
import java.sql.ResultSet

class PerformanceRowMapper : RowMapper<Performance> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Performance {
        return Performance(
            rs.getInt("id"),
            rs.getString("play_title"),
            rs.getString("play_genre"),
            rs.getString("author_first_name"),
            rs.getString("author_second_name"),
            rs.getInt("century"),
            rs.getDate("date").toLocalDate(),
            rs.getString("hall_title"),
            rs.getString("age_category"),
            rs.getDouble("base_price"),
            rs.getBoolean("is_premiere")
        )
    }

}
