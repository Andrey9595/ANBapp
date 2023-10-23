package ru.anb.myapplication.features.posts.domain

import ru.anb.myapplication.features.home.domain.ContentInteraction
import ru.anb.myapplication.features.home.domain.model.PostModel

interface PostInteraction : ContentInteraction<PostModel> {
}