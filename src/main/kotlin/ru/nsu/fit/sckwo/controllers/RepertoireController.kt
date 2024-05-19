package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.nsu.fit.sckwo.model.entitiies.Repertoire
import ru.nsu.fit.sckwo.repositories.RepertoireRepository

@Controller
@RequestMapping("/repertoires")
@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class RepertoireController @Autowired constructor(private val repertoireRepository: RepertoireRepository) {
    @GetMapping("")
    fun getAllRepertoires(): ResponseEntity<List<Repertoire>> {
        return ResponseEntity.ok(repertoireRepository.getAllRepertoires())
    }
}