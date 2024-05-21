package ru.nsu.fit.sckwo.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nsu.fit.sckwo.model.entities.Employee
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
        goneOnTour: Boolean?,
        cameOnTour: Boolean?,
        tourStartDate: String?,
        tourEndDate: String?,
        tourPlayId: Int?,
        performanceId: Int?,
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
        if (employeeType == EmployeeType.Manager || employeeType == EmployeeType.Any) {
            return jdbcTemplate.query(sqlQueryBuilder.build(), EmployeeRowMapper())
        }

        if (goneOnTour != null) {
            if (goneOnTour) {
                sqlQueryBuilder = sqlQueryBuilder
                    .leftJoin("artists_tours")
                    .on("artists.id = artists_tours.artist_id")
                    .leftJoin("tours")
                    .on("tours.id = artists_tours.tour_id")
                    .where("tours.organization_theater_id = employees.theater_id")
                    .where("tours.organization_theater_id IS NOT NULL")
            } else {
                sqlQueryBuilder = sqlQueryBuilder
                    .leftJoin("artists_tours")
                    .on("artists.id = artists_tours.artist_id")
                    .leftJoin("tours")
                    .on("tours.id = artists_tours.tour_id")
                    .where("tours.organization_theater_id = employees.theater_id")
                    .where("tours.organization_theater_id IS NULL")
            }
        }
        if (cameOnTour != null) {
            if (cameOnTour) {
                sqlQueryBuilder = sqlQueryBuilder
                    .leftJoin("artists_tours")
                    .on("artists.id = artists_tours.artist_id")
                    .leftJoin("tours")
                    .on("tours.id = artists_tours.tour_id")
                    .where("tours.tour_theater_id = employees.theater_id")
                    .where("tours.tour_theater_id IS NOT NULL")
            } else {
                sqlQueryBuilder = sqlQueryBuilder
                    .leftJoin("artists_tours")
                    .on("artists.id = artists_tours.artist_id")
                    .leftJoin("tours")
                    .on("tours.id = artists_tours.tour_id")
                    .where("tours.tour_theater_id = employees.theater_id")
                    .where("tours.tour_theater_id IS NULL")
            }
        }
        if (tourStartDate != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .leftJoin("artists_tours")
                .on("artists.id = artists_tours.artist_id")
                .leftJoin("tours")
                .on("tours.id = artists_tours.tour_id")
                .where("tours.date_of_start >= '$tourStartDate'")
        }
        if (tourEndDate != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .leftJoin("artists_tours")
                .on("artists.id = artists_tours.artist_id")
                .leftJoin("tours")
                .on("tours.id = artists_tours.tour_id")
                .where("tours.date_of_end >= '$tourEndDate'")
        }
        if (tourPlayId != null) {
            sqlQueryBuilder = sqlQueryBuilder
                .leftJoin("artists_tours")
                .on("artists.id = artists_tours.artist_id")
                .leftJoin("tours")
                .on("tours.id = artists_tours.tour_id")
                .where("tours.play_id = $tourPlayId")
        }
        if (performanceId != null) {
            when (employeeType) {
                EmployeeType.Musician -> {
                    sqlQueryBuilder = sqlQueryBuilder
                        .leftJoin("musicians_performances")
                        .on("musicians_performances.musician_id = musicians.id")
                        .where("musicians_performances.performance_id = $performanceId")
                }

                EmployeeType.Actor -> {
                    sqlQueryBuilder = sqlQueryBuilder
                        .leftJoin("castings")
                        .on("castings.actor_id = actors.id")
                        .where("castings.performance_id = $performanceId")
                }

                EmployeeType.ProductionDirector -> {
                    sqlQueryBuilder = sqlQueryBuilder
                        .leftJoin("production_directors_performances")
                        .on("production_directors_performances.production_director_id = production_directors.id")
                        .where("musicians_performances.performance_id = $performanceId")
                }

                EmployeeType.ProductionDesigner -> {
                    sqlQueryBuilder = sqlQueryBuilder
                        .leftJoin("production_designers_performances")
                        .on("production_designers_performances.production_designer_id = production_designers.id")
                        .where("production_designers_performances.performance_id = $performanceId")
                }

                EmployeeType.StageConductor -> {
                    sqlQueryBuilder = sqlQueryBuilder
                        .leftJoin("stage_conductors_performances")
                        .on("stage_conductors_performances.stage_conductor_id = stage_conductors.id")
                        .where("stage_conductors_performances.performance_id = $performanceId")
                }

                else -> {}
            }
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
                        "employees.patronymic," +
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
                        "employees.patronymic," +
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
                        "employees.patronymic," +
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
                        "employees.patronymic," +
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
                        "employees.patronymic," +
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
                        "employees.patronymic," +
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
                        "employees.patronymic," +
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
                        "employees.patronymic," +
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
                "patronymic",
                "gender",
                "date_of_birth",
                "date_of_employment",
                "amount_of_children",
                "salary"
            )
            .values(
                employee.firstName,
                employee.secondName,
                employee.patronymic,
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
                    "patronymic" to employee.patronymic,
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
