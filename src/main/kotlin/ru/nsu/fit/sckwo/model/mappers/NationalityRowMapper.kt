package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entitiies.Nationality
import java.sql.ResultSet

class NationalityRowMapper : RowMapper<Nationality> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Nationality {
        return Nationality(
            rs.getInt("id"),
            rs.getString("name"),
        )
    }
}