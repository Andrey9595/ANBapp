package ru.anb.myapplication.features.home.domain

import ru.anb.myapplication.core.domain.AppLoadState

interface BaseContentCRUD<T : Any> {

    suspend fun likeById(t: T)

    suspend fun dislikeById(t: T)

    suspend fun remove(id: Long): AppLoadState<Unit>

}