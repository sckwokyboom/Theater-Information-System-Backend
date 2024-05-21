package ru.nsu.fit.sckwo.model.mappers

import org.springframework.jdbc.core.RowMapper
import ru.nsu.fit.sckwo.model.entities.Employee
import java.sql.ResultSet

class EmployeeRowMapper : RowMapper<Employee> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Employee {
        return Employee(
            rs.getInt("id"),
            rs.getString("first_name"),
            rs.getString("second_name"),
            rs.getString("patronymic"),
            rs.getDate("date_of_birth").toLocalDate(),
            rs.getDate("date_of_employment").toLocalDate(),
            rs.getString("gender"),
            rs.getInt("amount_of_children"),
            rs.getInt("salary")
        )
    }
}