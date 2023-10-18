package ru.anb.myapplication.features.home.domain.model

import com.google.gson.annotations.SerializedName

data class AttachmentModel(
    val url: String,
    @SerializedName("type")
    val attachmentType: AttachmentType,
)
enum class AttachmentType {
    IMAGE, VIDEO, AUDIO
}

//fun AttachmentModel.toAttachModel() = AttachModel(
//    uri = url.toUri(),
//    type = type
//)