package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.nsu.fit.sckwo.model.entities.Employee
import ru.nsu.fit.sckwo.repositories.EmployeeRepository
import ru.nsu.fit.sckwo.repositories.EmployeeType

@Controller
@RequestMapping("/employees")
//@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class EmployeeController @Autowired constructor(private val employeeRepository: EmployeeRepository) {
    @GetMapping("/filter")
    fun getFilterEmployees(
        @RequestParam(required = false) employeeTypeName: String?,
        @RequestParam(required = false) gender: String?,
        @RequestParam(required = false) minSalary: Int?,
        @RequestParam(required = false) maxSalary: Int?,
        @RequestParam(required = false) amountOfChildren: Int?,
        @RequestParam(required = false) goneOnTour: Boolean?,
        @RequestParam(required = false) cameOnTour: Boolean?,
        @RequestParam(required = false) tourStartDate: String?,
        @RequestParam(required = false) tourEndDate: String?,
        @RequestParam(required = false) tourPlayId: Int?,
        @RequestParam(required = false) performanceId: Int?,
        @RequestParam(required = false) yearsOfService: Int?,
        @RequestParam(required = false) yearOfBirth: Int?,
        @RequestParam(required = false) age: Int?,
        @RequestParam(required = false) haveChildren: Boolean?,
    ): ResponseEntity<List<Employee>> {
        val employeeType: EmployeeType = if (employeeTypeName != null) {
            EmployeeType.valueOf(employeeTypeName)
        } else {
            EmployeeType.Any
        }
        return ResponseEntity.ok(
            employeeRepository.getEmployees(
                employeeType,
                gender,
                minSalary,
                maxSalary,
                amountOfChildren,
                goneOnTour,
                cameOnTour,
                tourStartDate,
                tourEndDate,
                tourPlayId,
                performanceId,
                yearsOfService,
                yearOfBirth,
                age,
                haveChildren
            )
        )
    }


    @PostMapping
    fun createEmployee(@RequestBody employee: Employee): ResponseEntity<Employee> {
        employeeRepository.insert(employee)
        return ResponseEntity.status(HttpStatus.CREATED).body(employee)
    }

    @PutMapping("/{id}")
    fun updateEmployee(@PathVariable id: Int, @RequestBody updatedEmployee: Employee): ResponseEntity<Employee> {
        if (!employeeRepository.existsById(id)) {
            return ResponseEntity.notFound().build()
        }
        employeeRepository.update(updatedEmployee)
        return ResponseEntity.ok(updatedEmployee)
    }

    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id: Int): ResponseEntity<Void> {
        if (!employeeRepository.existsById(id)) {
            return ResponseEntity.notFound().build()
        }
        employeeRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}