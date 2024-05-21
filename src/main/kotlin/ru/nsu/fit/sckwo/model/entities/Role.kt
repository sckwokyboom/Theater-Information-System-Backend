package ru.nsu.fit.sckwo.model.entities

data class Role(
    val id: Int,
    val name: String,
    val weight: Int?,
    val height: Int?,
    val eyeColor: String?,
    val skinColor: String?,
    val hairColor: String?,
    val voiceType: String?,
    val gender: String?,
    val age: Int?,
    val playId: Int,
    val description: String?,
    val nationalityId: Int?,
)
