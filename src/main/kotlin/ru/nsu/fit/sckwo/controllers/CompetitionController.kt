package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.nsu.fit.sckwo.model.entities.Competition
import ru.nsu.fit.sckwo.repositories.CompetitionRepository

@Controller
@RequestMapping("/competitions")
@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class CompetitionController @Autowired constructor(private val competitionRepository: CompetitionRepository) {
    @GetMapping("")
    fun getAllTitles(): ResponseEntity<List<Competition>> {
        return ResponseEntity.ok(competitionRepository.getAllCompetitions())
    }
}