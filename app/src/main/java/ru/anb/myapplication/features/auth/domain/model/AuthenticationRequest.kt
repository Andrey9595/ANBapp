package ru.anb.myapplication.features.auth.domain.model

data class AuthenticationRequest(
    val login: String,
    val password: String
)