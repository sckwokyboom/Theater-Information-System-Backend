package ru.nsu.fit.sckwo.model.entitiies

import java.time.LocalDate

data class Repertoire(
    val id: Int,
    val startOfSeason: LocalDate,
    val endOfSeason: LocalDate,
)
