package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.nsu.fit.sckwo.model.entitiies.Casting
import ru.nsu.fit.sckwo.repositories.CastingRepository

@Controller
@RequestMapping("/castings")
@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class CastingController @Autowired constructor(private val castingRepository: CastingRepository) {
    @GetMapping("")
    fun getAllCastings(): ResponseEntity<List<Casting>> {
        return ResponseEntity.ok(
            castingRepository.getAllCastings()
        )
    }

    @GetMapping("/filter")
    fun getFilterCastings(
        @RequestParam(required = false) actorId: Int?,
        @RequestParam(required = false) dateOfStart: String?,
        @RequestParam(required = false) dateOfEnd: String?,
        @RequestParam(required = false) playGenreId: Int?,
        @RequestParam(required = false) productionDirectorId: Int?,
        @RequestParam(required = false) ageCategory: String?,
    ): ResponseEntity<List<Casting>> {
        return ResponseEntity.ok(
            castingRepository.getCastings(
                actorId, dateOfStart, dateOfEnd, playGenreId, productionDirectorId, ageCategory
            )
        )
    }
}