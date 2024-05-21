package ru.nsu.fit.sckwo.model.entities

data class BuySubscriptionRequest(
    val performanceIds: List<Int>,
    val placeIds: List<Int>,
)
