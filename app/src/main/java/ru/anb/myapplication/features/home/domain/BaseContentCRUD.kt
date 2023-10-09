package ru.anb.myapplication.features.home.domain

interface BaseContentCRUD<T : Any, R : Any> {

    suspend fun likeById(t: T)

    suspend fun dislikeById(t: T)

}