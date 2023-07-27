package ru.anb.myapplication.features.auth.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject

class PersistentStoreImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    PersistentStore {

    private val tokenKay = stringPreferencesKey("token")

    override suspend fun saveToken(token: String) {
        dataStore.edit { prefs -> prefs[tokenKay] = token }
    }

    override suspend fun isAuthorized(): Boolean {
        return dataStore.edit { }.contains(tokenKay)
    }
}