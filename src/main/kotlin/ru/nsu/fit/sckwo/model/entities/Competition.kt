package ru.nsu.fit.sckwo.model.entities

import java.time.LocalDate

data class Competition(
    val id: Int,
    val name: String,
    val dateOfStart: LocalDate,
    val dateOfEnd: LocalDate,
)
