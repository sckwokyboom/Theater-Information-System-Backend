package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.nsu.fit.sckwo.model.entitiies.Country
import ru.nsu.fit.sckwo.repositories.CountryRepository

@Controller
@RequestMapping("/countries")
@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class CountryController @Autowired constructor(private val countryRepository: CountryRepository) {
    @GetMapping("/all")
    fun getAllCountries(): ResponseEntity<List<Country>> {
        return ResponseEntity.ok(
            countryRepository.getAllCountries()
        )
    }
}