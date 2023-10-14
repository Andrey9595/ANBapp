package ru.anb.myapplication.features.home.domain.model.job

data class JobCreateRequest(
    val id: Long,
    val name: String,
    val position: String,
    val start: String,
    val finish: String?,
    val link: String?
)
