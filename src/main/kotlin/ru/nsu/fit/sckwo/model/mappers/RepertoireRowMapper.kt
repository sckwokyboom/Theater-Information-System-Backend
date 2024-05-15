package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entitiies.Repertoire
import java.sql.ResultSet

class RepertoireRowMapper : RowMapper<Repertoire> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Repertoire {
        return Repertoire(
            rs.getInt("id"),
            rs.getDate("start_of_season").toLocalDate(),
            rs.getDate("end_of_season").toLocalDate(),
        )
    }
}