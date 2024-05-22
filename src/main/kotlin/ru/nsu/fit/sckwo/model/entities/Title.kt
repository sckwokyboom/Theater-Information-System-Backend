package ru.nsu.fit.sckwo.model.entities

data class Title(
    val id: Int,
    val name: String,
    val competitionId: Int,
    val competitionName: String,
)