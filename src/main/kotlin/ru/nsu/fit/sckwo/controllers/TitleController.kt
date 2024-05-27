package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.nsu.fit.sckwo.model.entities.Title
import ru.nsu.fit.sckwo.repositories.TitleRepository

@Controller
@RequestMapping("/titles")
//@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class TitleController @Autowired constructor(private val titleRepository: TitleRepository) {
    @GetMapping("")
    fun getAllTitles(): ResponseEntity<List<Title>> {
        return ResponseEntity.ok(titleRepository.getAllTitles())
    }
}