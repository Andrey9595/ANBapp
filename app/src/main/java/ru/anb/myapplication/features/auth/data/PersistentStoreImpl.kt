package ru.anb.myapplication.features.auth.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject

class PersistentStoreImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    PersistentStore {

    private val tokenKay = stringPreferencesKey("token")
    private val idKey = intPreferencesKey("id")

    override suspend fun saveToken(token: String) {
        dataStore.edit { prefs -> prefs[tokenKay] = token }
    }

    override suspend fun savaId(id: Int) {
       dataStore.edit { prefs -> prefs[idKey] = id }
    }

    override suspend fun getUserId(): Int? {
       return dataStore.edit { }[idKey]
    }

    override suspend fun isAuthorized(): Boolean {
        return dataStore.edit { }.contains(tokenKay)
    }

    override suspend fun removeToken(): Boolean {
        return try {
            dataStore.edit {
                it.remove(tokenKay)
                it.remove(idKey)
            }
            true
        } catch (e: Exception){
            false
        }
    }
}