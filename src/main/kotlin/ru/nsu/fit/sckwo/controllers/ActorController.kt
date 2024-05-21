package ru.nsu.fit.sckwo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.nsu.fit.sckwo.model.entities.Actor
import ru.nsu.fit.sckwo.repositories.ActorRepository

@Controller
@RequestMapping("/actors")
@CrossOrigin(origins = ["http://127.0.0.1:5173"])
class ActorController @Autowired constructor(private val actorRepository: ActorRepository) {
    @GetMapping("")
    fun getAllActors(): ResponseEntity<List<Actor>> {
        return ResponseEntity.ok(
            actorRepository.getAllActors()
        )
    }

    @GetMapping("/filter")
    fun getFilterActors(
        @RequestParam(required = false) roleWeight: Int?,
        @RequestParam(required = false) roleHeight: Int?,
        @RequestParam(required = false) roleEyeColor: String?,
        @RequestParam(required = false) roleSkinColor: String?,
        @RequestParam(required = false) roleHairColor: String?,
        @RequestParam(required = false) roleVoiceType: String?,
        @RequestParam(required = false) roleGender: String?,
        @RequestParam(required = false) roleAge: Int?,
        @RequestParam(required = false) roleNationalityId: Int?,
        @RequestParam(required = false) titleId: Int?,
        @RequestParam(required = false) age: Int?,
        @RequestParam(required = false) gender: String?,
        @RequestParam(required = false) dateOfStartForTitle: String?,
        @RequestParam(required = false) dateOfEndForTitle: String?,
    ): ResponseEntity<List<Actor>> {
        return ResponseEntity.ok(
            actorRepository.getFilterActors(
                roleWeight,
                roleHeight,
                roleEyeColor,
                roleSkinColor,
                roleHairColor,
                roleVoiceType,
                roleGender,
                roleAge,
                roleNationalityId,
                titleId,
                age,
                gender,
                dateOfStartForTitle,
                dateOfEndForTitle
            )
        )
    }
}