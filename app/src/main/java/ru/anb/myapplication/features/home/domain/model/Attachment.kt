package ru.anb.myapplication.features.home.domain.model

import com.google.gson.annotations.SerializedName

data class Attachment(
    val url: String,
   @SerializedName("type")
    val attachmentType: String?
)
