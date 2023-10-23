package ru.anb.myapplication.features.newpost.domain.model

import ru.anb.myapplication.features.home.domain.model.AttachmentModel
import ru.anb.myapplication.features.home.domain.model.UserPreview

data class PostResponse(
    val id: Long,
    val authorId: Long,
    val author: String,
    val authorAvatar: String?,
    val authorJob: String?,
    val content: String,
    val published:String,
//    val coords: Coordinates?,
    val link: String?,
    val likeOwnerIds: List<Long>,
    val mentionIds: List<Long>,
    val mentionMe: Boolean,
    val likedByMe: Boolean,
    val attachment: AttachmentModel?,
    val ownedByMe: Boolean,
    val users: Map<Long, UserPreview>
)
