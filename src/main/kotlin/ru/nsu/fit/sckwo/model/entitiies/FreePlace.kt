package ru.nsu.fit.sckwo.model.entitiies

data class FreePlace(
    val id: Int,
    val hallTitle: String,
    val priceCoefficient: Double,
    val performanceId: Int,
)
