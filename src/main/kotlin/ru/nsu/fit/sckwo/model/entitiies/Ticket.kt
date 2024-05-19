package ru.nsu.fit.sckwo.model.entitiies

data class Ticket(
    val id: Int,
    val playTitle: String,
    val price: Double,
    val hallTitle: String,
    val placeId: Int,
)