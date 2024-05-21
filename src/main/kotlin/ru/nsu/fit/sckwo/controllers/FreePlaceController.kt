package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.nsu.fit.sckwo.model.entities.FreePlace
import ru.nsu.fit.sckwo.repositories.FreePlaceRepository

@Controller
@RequestMapping("/places")
@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class FreePlaceController @Autowired constructor(private val freePlaceRepository: FreePlaceRepository) {

    @GetMapping("/filter")
    fun getFilterFreePlaces(
        @RequestParam(required = false) performanceId: Int?,
        @RequestParam(required = false) isPremiere: String?,
        @RequestParam(required = false) isUpcomingPerformances: String?,
    ): ResponseEntity<List<FreePlace>> {
        return ResponseEntity.ok(
            freePlaceRepository.getPlaces(performanceId, isPremiere, isUpcomingPerformances)
        )
    }
}
