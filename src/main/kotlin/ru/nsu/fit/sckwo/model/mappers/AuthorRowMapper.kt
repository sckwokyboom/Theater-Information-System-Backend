package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entitiies.Author
import java.sql.ResultSet

class AuthorRowMapper : RowMapper<Author> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Author {
        return Author(
            rs.getInt("id"),
            rs.getString("first_name"),
            rs.getString("second_name"),
            rs.getString("patronymic"),
            if (rs.getDate("date_of_birth") == null) null else rs.getDate("date_of_birth").toLocalDate(),
            if (rs.getDate("date_of_death") == null) null else rs.getDate("date_of_death").toLocalDate(),
            rs.getString("country_of_origin_name"),
        )
    }
}