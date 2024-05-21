package ru.nsu.fit.sckwo.model.entities

import java.time.LocalDate

data class Casting(
    val actorId: Int,
    val actorFirstName: String,
    val actorSecondName: String,
    val playTitle: String,
    val performanceDate: LocalDate,
    val performanceId: Int,
    val doubleId: Int?,
    val roleId: Int,
    val roleName: String,
    val roleDescription: String,
)