package ru.nsu.fit.sckwo.model.entitiies

import java.time.LocalDate

data class Employee(
    val id: Int,
    val firstName: String,
    val secondName: String,
    val dateOfBirth: LocalDate,
    val dateOfEmployment: LocalDate,
    val gender: String,
    val amountOfChildren: Int,
    val salary: Int,
)
