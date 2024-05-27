package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.nsu.fit.sckwo.model.entities.Performance
import ru.nsu.fit.sckwo.repositories.PerformanceRepository

@Controller
@RequestMapping("/performances")
//@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class PerformanceController @Autowired constructor(private val performanceRepository: PerformanceRepository) {
    @GetMapping("/filter")
    fun getFilterPerformances(
        @RequestParam(required = false) repertoireId: Int?,
        @RequestParam(required = false) isPremiere: Boolean?,
        @RequestParam(required = false) genreId: Int?,
        @RequestParam(required = false) dateOfStart: String?,
        @RequestParam(required = false) dateOfEnd: String?,
        @RequestParam(required = false) authorId: Int?,
        @RequestParam(required = false) authorCountryId: Int?,
        @RequestParam(required = false) centuryOfPlayWriting: Int?,
        @RequestParam(required = false) isUpcoming: Boolean?,
    ): ResponseEntity<List<Performance>> {
        return ResponseEntity.ok(
            performanceRepository.getPerformances(
                repertoireId,
                isPremiere,
                genreId,
                dateOfStart,
                dateOfEnd,
                authorId,
                authorCountryId,
                centuryOfPlayWriting,
                isUpcoming
            )
        )
    }
}