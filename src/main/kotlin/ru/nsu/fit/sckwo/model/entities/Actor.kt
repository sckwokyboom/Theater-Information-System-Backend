package ru.nsu.fit.sckwo.model.entities

data class Actor(
    val id: Int,
    val firstName: String,
    val secondName: String,
    val patronymic: String,
    val artistId: Int,
    val voiceType: String,
    val weight: Int?,
    val height: Int?,
    val hairColor: String?,
    val eyeColor: String?,
    val skinColor: String?,
    val nationalityId: Int?,
    val nationalityName: String?,
)
