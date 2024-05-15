package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.nsu.fit.sckwo.model.entitiies.Employee
import ru.nsu.fit.sckwo.repositories.EmployeeRepository
import ru.nsu.fit.sckwo.repositories.EmployeeType

@Controller
@RequestMapping("/employees")
@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class EmployeeController @Autowired constructor(private val employeeRepository: EmployeeRepository) {
    @GetMapping("/filter")
    fun getFilterEmployees(
        @RequestParam(required = false) employeeTypeName: String?,
        @RequestParam(required = false) gender: String?,
        @RequestParam(required = false) minSalary: Int?,
        @RequestParam(required = false) maxSalary: Int?,
        @RequestParam(required = false) amountOfChildren: Int?,
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
                amountOfChildren
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