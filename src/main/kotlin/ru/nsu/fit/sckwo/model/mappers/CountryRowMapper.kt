package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entitiies.Country
import java.sql.ResultSet

class CountryRowMapper : RowMapper<Country> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Country {
        return Country(
            rs.getInt("id"),
            rs.getString("name")
        )
    }

}
