package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entitiies.Employee
import ru.nsu.fit.sckwo.model.mappers.EmployeeRowMapper
import ru.nsu.fit.sckwo.utils.SqlQueryBuilder

@Repository
class EmployeeRepository(private val jdbcTemplate: JdbcTemplate) {
    fun getEmployees(
        employeeType: EmployeeType,
        gender: String?,
        minSalary: Int?,
        maxSalary: Int?,
        amountOfChildren: Int?,
    ): List<Employee> {
        var sqlQueryBuilder = when (employeeType) {
            EmployeeType.Any -> getAllEmployeesSqlQueryBuilder()
            EmployeeType.Musician -> getAllMusiciansSqlQueryBuilder()
            EmployeeType.Actor -> getAllActorsSqlQueryBuilder()
            EmployeeType.Artist -> getAllArtistsSqlQueryBuilder()
            EmployeeType.Director -> getAllDirectorsSqlQueryBuilder()
            EmployeeType.Manager -> getAllManagersSqlQueryBuilder()
            EmployeeType.ProductionDirector -> getAllProductionDirectorsSqlQueryBuilder()
            EmployeeType.ProductionDesigner -> getAllProductionDesignersSqlQueryBuilder()
            EmployeeType.StageConductor -> getAllStageConductorsSqlQueryBuilder()
        }

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

    private fun getAllEmployeesSqlQueryBuilder(): SqlQueryBuilder {
        return SqlQueryBuilder()
            .select("*")
            .from("employees")
    }

    private fun getAllMusiciansSqlQueryBuilder(): SqlQueryBuilder {
        return SqlQueryBuilder()
            .select(
                "employees.id," +
                        "employees.first_name," +
                        "employees.second_name," +
                        "employees.gender," +
                        "employees.date_of_birth," +
                        "employees.date_of_employment," +
                        "employees.amount_of_children," +
                        "employees.salary"
            )
            .from("musicians")
            .leftJoin("artists")
            .on("artist_id = artists.id")
            .leftJoin("employees")
            .on("employee_id = employees.id")
    }

    private fun getAllActorsSqlQueryBuilder(): SqlQueryBuilder {
        return SqlQueryBuilder()
            .select(
                "employees.id," +
                        "employees.first_name," +
                        "employees.second_name," +
                        "employees.gender," +
                        "employees.date_of_birth," +
                        "employees.date_of_employment," +
                        "employees.amount_of_children," +
                        "employees.salary"
            )
            .from("actors")
            .leftJoin("artists")
            .on("artist_id = artists.id")
            .leftJoin("employees")
            .on("employee_id = employees.id")
    }

    private fun getAllArtistsSqlQueryBuilder(): SqlQueryBuilder {
        return SqlQueryBuilder()
            .select(
                "employees.id," +
                        "employees.first_name," +
                        "employees.second_name," +
                        "employees.gender," +
                        "employees.date_of_birth," +
                        "employees.date_of_employment," +
                        "employees.amount_of_children," +
                        "employees.salary"
            )
            .from("artists")
            .leftJoin("employees")
            .on("employee_id = employees.id")
    }

    private fun getAllDirectorsSqlQueryBuilder(): SqlQueryBuilder {
        return SqlQueryBuilder()
            .select(
                "employees.id," +
                        "employees.first_name," +
                        "employees.second_name," +
                        "employees.gender," +
                        "employees.date_of_birth," +
                        "employees.date_of_employment," +
                        "employees.amount_of_children," +
                        "employees.salary"
            )
            .from("directors")
            .leftJoin("artists")
            .on("artist_id = artists.id")
            .leftJoin("employees")
            .on("employee_id = employees.id")
    }

    private fun getAllProductionDirectorsSqlQueryBuilder(): SqlQueryBuilder {
        return SqlQueryBuilder()
            .select(
                "employees.id," +
                        "employees.first_name," +
                        "employees.second_name," +
                        "employees.gender," +
                        "employees.date_of_birth," +
                        "employees.date_of_employment," +
                        "employees.amount_of_children," +
                        "employees.salary"
            )
            .from("production_directors")
            .leftJoin("directors")
            .on("director_id = directors.id")
            .leftJoin("artists")
            .on("artist_id = artists.id")
            .leftJoin("employees")
            .on("employee_id = employees.id")
    }

    private fun getAllProductionDesignersSqlQueryBuilder(): SqlQueryBuilder {
        return SqlQueryBuilder()
            .select(
                "employees.id," +
                        "employees.first_name," +
                        "employees.second_name," +
                        "employees.gender," +
                        "employees.date_of_birth," +
                        "employees.date_of_employment," +
                        "employees.amount_of_children," +
                        "employees.salary"
            )
            .from("production_designers")
            .leftJoin("directors")
            .on("director_id = directors.id")
            .leftJoin("artists")
            .on("artist_id = artists.id")
            .leftJoin("employees")
            .on("employee_id = employees.id")
    }

    private fun getAllStageConductorsSqlQueryBuilder(): SqlQueryBuilder {
        return SqlQueryBuilder()
            .select(
                "employees.id," +
                        "employees.first_name," +
                        "employees.second_name," +
                        "employees.gender," +
                        "employees.date_of_birth," +
                        "employees.date_of_employment," +
                        "employees.amount_of_children," +
                        "employees.salary"
            )
            .from("stage_conductors")
            .leftJoin("directors")
            .on("director_id = directors.id")
            .leftJoin("artists")
            .on("artist_id = artists.id")
            .leftJoin("employees")
            .on("employee_id = employees.id")
    }

    private fun getAllManagersSqlQueryBuilder(): SqlQueryBuilder {
        return SqlQueryBuilder()
            .select(
                "employees.id," +
                        "employees.first_name," +
                        "employees.second_name," +
                        "employees.gender," +
                        "employees.date_of_birth," +
                        "employees.date_of_employment," +
                        "employees.amount_of_children," +
                        "employees.salary"
            )
            .from("managers")
            .leftJoin("employees")
            .on("employee_id = employees.id")
    }

    fun existsById(id: Int): Boolean {
        val sqlQuery = SqlQueryBuilder()
            .select("COUNT(*)")
            .from("employees")
            .where("id = $id")
            .build()
        val count = jdbcTemplate.queryForObject(sqlQuery, Int::class.java)
        return count != null && (count > 0)
    }

    fun deleteById(id: Int) {
        val sqlQuery = SqlQueryBuilder()
            .delete()
            .from("employees")
            .where("id = $id")
            .build()
        jdbcTemplate.update(sqlQuery)
    }

    fun insert(employee: Employee) {
        val sqlQuery = SqlQueryBuilder()
            .insertInto(
                "employees",
                "first_name",
                "second_name",
                "gender",
                "date_of_birth",
                "date_of_employment",
                "amount_of_children",
                "salary"
            )
            .values(
                employee.firstName,
                employee.secondName,
                employee.gender,
                employee.dateOfBirth.toString(),
                employee.dateOfEmployment.toString(),
                employee.amountOfChildren.toString(),
                employee.salary.toString()
            )
            .build()

        jdbcTemplate.update(sqlQuery)
    }

    fun update(employee: Employee) {
        val sqlQuery = SqlQueryBuilder()
            .update("employees")
            .set(
                mapOf(
                    "first_name" to employee.firstName,
                    "second_name" to employee.secondName,
                    "gender" to employee.gender,
                    "date_of_birth" to "${employee.dateOfBirth}",
                    "date_of_employment" to "${employee.dateOfEmployment}",
                    "amount_of_children" to "${employee.amountOfChildren}",
                    "salary" to "${employee.salary}"
                )
            )
            .where("id = ${employee.id}")
            .build()
        jdbcTemplate.update(sqlQuery)
    }
}
