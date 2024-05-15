package ru.nsu.fit.sckwo.model.entitiies

data class Ticket(
    val id: Int,
    val performanceId: Int,
    val price: Double,
    val placeId: Int,
    val subscriptionId: Int,
)