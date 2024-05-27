package ru.nsu.fit.sckwo.model.entities

import java.time.LocalDate

data class Ticket(
    val id: Int?,
    val performanceId: Int,
    val price: Double,
    val playTitle: String?,
    val hallTitle: String?,
    val placeId: Int,
    val subscriptionId: Int?,
    val saleDate: LocalDate?,
)