package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entitiies.Author
import ru.nsu.fit.sckwo.model.entitiies.Employee
import ru.nsu.fit.sckwo.model.mappers.AuthorRowMapper
import ru.nsu.fit.sckwo.model.mappers.EmployeeRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class AuthorRepository(private val jdbcTemplate: JdbcTemplate) {
    fun getAllAuthors(): List<Author> {
        val sqlQuery = SqlQueryBuilder()
            .select("authors.id, first_name, second_name, patronymic, date_of_birth, date_of_death, countries.name country_of_origin_name")
            .from("authors")
            .leftJoin("countries")
            .on("authors.country_of_origin_id = countries.id")
            .build()
        return jdbcTemplate.query(sqlQuery, AuthorRowMapper())
    }

    fun getFilterAuthors(
        wasPerformed: Boolean?,
        centuryOfLiving: Int?,
        countryOfOriginId: Int?,
        genreId: Int?,
        dateOfStartPerformanceAuthorsPlays: Int?,
        dateOfEndPerformanceAuthorsPlays: Int?,
    ): List<Employee>? {
        val sqlQueryBuilder = SqlQueryBuilder()
            .select("authors.id, first_name, second_name, patronymic, date_of_birth, date_of_death, countries.name country_of_origin_name")
            .from("authors")
            .leftJoin("countries")
            .on("authors.country_of_origin_id = countries.id")
        if (gender != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("gender = \'$gender\'")
        }
        if (minSalary != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("salary >= $minSalary")
        }
        if (maxSalary != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("salary <= $maxSalary")
        }
        if (amountOfChildren != null) {
            sqlQueryBuilder = sqlQueryBuilder.where("amount_of_children = $amountOfChildren")
        }

        return jdbcTemplate.query(sqlQueryBuilder.build(), EmployeeRowMapper())
    }
}
