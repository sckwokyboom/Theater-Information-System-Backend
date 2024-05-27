package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.nsu.fit.sckwo.model.entities.Nationality
import ru.nsu.fit.sckwo.repositories.NationalityRepository

@Controller
@RequestMapping("/nationalities")
//@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class NationalityController @Autowired constructor(private val nationalityRepository: NationalityRepository) {
    @GetMapping("")
    fun getAllNationalities(): ResponseEntity<List<Nationality>> {
        return ResponseEntity.ok(
            nationalityRepository.getAllNationalities()
        )
    }
}