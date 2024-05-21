package ru.nsu.fit.sckwo.model.entities

import java.time.LocalDateTime

data class Performance(
    val id: Int,
    val playTitle: String,
    val playGenre: String,
    val authorFirstName: String?,
    val authorSecondName: String?,
    val centuryOfPlayWriting: Int,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val hallTitle: String,
    val ageCategory: String,
    val basePrice: Double,
    val isPremiere: Boolean,
)
