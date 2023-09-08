package ru.anb.myapplication.features.home.domain.model

data class AttachmentModel(
    val url: String,
    val type: AttachmentType,
)
enum class AttachmentType {
    IMAGE, VIDEO, AUDIO
}

//fun AttachmentModel.toAttachModel() = AttachModel(
//    uri = url.toUri(),
//    type = type
//)