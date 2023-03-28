package com.example.flightsearchapp.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferenceRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        const val TAG = "UserPreferenceRepo"
        val SEARCH_STRING_VALUE = stringPreferencesKey(name = "search_string")
    }

    val searchStringValue: Flow<String> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading search string value: $it")
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { prefrence -> prefrence[SEARCH_STRING_VALUE] ?: "" }

    suspend fun saveSearchStringPreference(searchStringValue: String) {
        dataStore.edit {
            it[SEARCH_STRING_VALUE] = searchStringValue
        }
    }
}