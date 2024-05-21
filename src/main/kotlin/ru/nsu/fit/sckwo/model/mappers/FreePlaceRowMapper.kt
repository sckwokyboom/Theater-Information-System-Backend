package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entities.FreePlace
import java.sql.ResultSet

class FreePlaceRowMapper : RowMapper<FreePlace> {
    override fun mapRow(rs: ResultSet, rowNum: Int): FreePlace {
        return FreePlace(
            rs.getInt("id"),
            rs.getString("hall_title"),
            rs.getDouble("price_coefficient"),
            rs.getInt("performance_id"),
        )
    }
}