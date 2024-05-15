package ru.nsu.fit.sckwo.model.entitiies

import java.time.LocalDate

data class Performance(
    val id: Int,
    val playTitle: String,
    val playGenre: String,
    val authorFirstName: String?,
    val authorSecondName: String?,
    val centuryOfPlayWriting: Int,
    val date: LocalDate,
    val hallTitle: String,
    val ageCategory: String,
    val basePrice: Double,
    val isPremiere: Boolean,
)
