package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entitiies.Ticket
import java.sql.ResultSet

class TicketRowMapper : RowMapper<Ticket> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Ticket {
        return Ticket(
            rs.getInt("id"),
            rs.getString("play_title"),
            rs.getDouble("price"),
            rs.getString("hall_title"),
            rs.getInt("place_id"),
        )
    }
}