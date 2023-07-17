package ru.anb.myapplication.features.auth.domain.model


data class RegistrationBody(
    val login: String,
    val password: String,
    val name: String,
    val avatar: String? = null

)
