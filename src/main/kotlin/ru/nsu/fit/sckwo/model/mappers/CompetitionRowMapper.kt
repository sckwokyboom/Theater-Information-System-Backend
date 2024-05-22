package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entities.Competition
import java.sql.ResultSet

class CompetitionRowMapper : RowMapper<Competition> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Competition {
        return Competition(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getDate("date_of_start").toLocalDate(),
            rs.getDate("date_of_end").toLocalDate(),
        )
    }
}