package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.nsu.fit.sckwo.model.entities.Employee
import ru.nsu.fit.sckwo.model.entities.Role
import ru.nsu.fit.sckwo.repositories.RoleRepository

@Controller
@RequestMapping("/roles")
@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class RoleController @Autowired constructor(private val roleRepository: RoleRepository) {
    @GetMapping("")
    fun getAllRoles(): ResponseEntity<List<Role>> {
        return ResponseEntity.ok(roleRepository.getAllRoles())
    }

    @GetMapping("/filter")
    fun getFilterRoles(
        @RequestParam(required = false) actorId: Int?,
        @RequestParam(required = false) genreId: Int?,
        @RequestParam(required = false) productionDirectorId: Int?,
        @RequestParam(required = false) periodOfPerformancesStartDate: String?,
        @RequestParam(required = false) periodOfPerformancesEndDate: String?,
        @RequestParam(required = false) ageCategory: Int?,


        ): ResponseEntity<List<Employee>> {
        TODO()
    }

}