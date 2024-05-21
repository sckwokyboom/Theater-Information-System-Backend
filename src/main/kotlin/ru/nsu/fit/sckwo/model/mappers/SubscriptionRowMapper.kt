package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entities.Subscription
import java.sql.ResultSet

class SubscriptionRowMapper : RowMapper<Subscription> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Subscription {
        return Subscription(
            rs.getInt("id"),
            rs.getDouble("price")
        )
    }
}