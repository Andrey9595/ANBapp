package ru.anb.myapplication.features.auth.domain.model

data class AuthenticationBody(
    val login: String,
    val password: String
)