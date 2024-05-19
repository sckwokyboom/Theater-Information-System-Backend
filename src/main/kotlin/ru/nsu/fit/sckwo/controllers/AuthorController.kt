package ru.nsu.fit.sckwo.controllers


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.nsu.fit.sckwo.model.entitiies.Author
import ru.nsu.fit.sckwo.repositories.AuthorRepository

@Controller
@RequestMapping("/authors")
@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class AuthorController @Autowired constructor(private val authorRepository: AuthorRepository) {
    @GetMapping("")
    fun getAllAuthors(): ResponseEntity<List<Author>> {
        return ResponseEntity.ok(
            authorRepository.getAllAuthors()
        )
    }

    @GetMapping("/filter")
    fun getFilterAuthors(
        @RequestParam(required = false) wasPerformed: Boolean?,
        @RequestParam(required = false) centuryOfLiving: Int?,
        @RequestParam(required = false) countryOfOriginId: Int?,
        @RequestParam(required = false) genreId: Int?,
        @RequestParam(required = false) dateOfStartPerformanceAuthorsPlays: Int?,
        @RequestParam(required = false) dateOfEndPerformanceAuthorsPlays: Int?,
    ): ResponseEntity<List<Author>> {
        return ResponseEntity.ok(
            authorRepository.getFilterAuthors(
                wasPerformed,
                centuryOfLiving,
                countryOfOriginId,
                genreId,
                dateOfStartPerformanceAuthorsPlays,
                dateOfEndPerformanceAuthorsPlays
            )
        )
    }
}