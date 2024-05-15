package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.nsu.fit.sckwo.model.entitiies.Play
import ru.nsu.fit.sckwo.repositories.PlayRepository

@Controller
@RequestMapping("/plays")
@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class PlayController @Autowired constructor(private val playRepository: PlayRepository) {
    @GetMapping("/all")
    fun getAllPlays(): ResponseEntity<List<Play>> {
        return ResponseEntity.ok(playRepository.getAllPlays())
    }
}