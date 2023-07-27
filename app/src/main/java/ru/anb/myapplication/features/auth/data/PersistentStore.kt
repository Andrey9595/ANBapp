package ru.anb.myapplication.features.auth.data

interface PersistentStore {

    suspend fun saveToken(token: String)

    suspend fun isAuthorized(): Boolean
}