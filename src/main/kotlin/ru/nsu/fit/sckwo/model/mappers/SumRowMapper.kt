package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entitiies.Sum
import java.sql.ResultSet

class SumRowMapper : RowMapper<Sum> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Sum {
        return Sum(
            rs.getDouble("sum"),
        )
    }
}