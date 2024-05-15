package ru.nsu.fit.sckwo.model.entitiies

data class Actor(
    val id: Int,
    val artistId: Int,
    val voiceType: String,
    val weight: Int?,
    val height: Int?,
    val hairColor: String?,
    val eyeColor: String?,
    val skinColor: String?,
    val nationalityId: Int?,
)
