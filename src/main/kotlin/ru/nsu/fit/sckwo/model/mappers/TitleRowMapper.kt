package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entities.Title
import java.sql.ResultSet

class TitleRowMapper : RowMapper<Title> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Title {
        return Title(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("competition_id"),
            rs.getString("competition_name")
        )
    }
}