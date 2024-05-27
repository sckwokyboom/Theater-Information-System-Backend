package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.nsu.fit.sckwo.model.entities.Genre
import ru.nsu.fit.sckwo.repositories.GenreRepository

@Controller
@RequestMapping("/genres")
//@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class GenreController @Autowired constructor(private val genreRepository: GenreRepository) {
    @GetMapping("")
    fun getAllGenres(): ResponseEntity<List<Genre>> {
        return ResponseEntity.ok(
            genreRepository.getAllGenres()
        )
    }
}