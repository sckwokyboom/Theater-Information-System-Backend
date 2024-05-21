package ru.nsu.fit.sckwo.model.entities

import java.time.LocalDate

data class Author(
    val id: Int,
    val firstName: String,
    val secondName: String?,
    val patronymic: String?,
    val dateOfBirth: LocalDate?,
    val dateOfDeath: LocalDate?,
    val countryOfOriginName: String,
)
