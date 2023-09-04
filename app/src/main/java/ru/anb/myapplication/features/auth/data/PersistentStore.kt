package ru.anb.myapplication.features.auth.data

interface PersistentStore {

    suspend fun saveToken(token: String)

    suspend fun savaId(id: Int)

    suspend fun getUserId(): Int?

    suspend fun isAuthorized(): Boolean
}