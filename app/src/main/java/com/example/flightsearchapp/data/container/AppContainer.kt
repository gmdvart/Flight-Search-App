package com.example.flightsearchapp.data.container

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.flightsearchapp.data.database.FlightDatabase
import com.example.flightsearchapp.data.repository.FlightRepository
import com.example.flightsearchapp.data.repository.OfflineFlightRepository
import com.example.flightsearchapp.data.repository.UserPreferenceRepository

private const val SEARCH_STRING_NAME = "search_string"

private val Context.dataStore by preferencesDataStore(
    name = SEARCH_STRING_NAME
)

interface AppContainer {
    val userPreferenceRepository: UserPreferenceRepository
    val flightRepository: FlightRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val userPreferenceRepository: UserPreferenceRepository by lazy {
        UserPreferenceRepository(dataStore = context.dataStore)
    }
    override val flightRepository: FlightRepository by lazy { OfflineFlightRepository(FlightDatabase.getDatabase(context).getDao()) }
}