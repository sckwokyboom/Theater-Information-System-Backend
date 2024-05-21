package ru.nsu.fit.sckwo.model.entities

data class FreePlace(
    val id: Int,
    val hallTitle: String,
    val priceCoefficient: Double,
    val performanceId: Int,
)
