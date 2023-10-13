package ru.anb.myapplication.features.home.domain.model.job

data class JobModel(
    val userId: Long,
    val id: Long,
    val ownedByMe: Boolean,
    val name: String,
    val position: String,
    val start: String,
    val finish: String?,
    val link: String?
)
