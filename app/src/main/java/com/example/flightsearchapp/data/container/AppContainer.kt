package com.example.flightsearchapp.data.container

import android.content.Context
import com.example.flightsearchapp.data.database.FlightDatabase
import com.example.flightsearchapp.data.repository.FlightRepository
import com.example.flightsearchapp.data.repository.OfflineFlightRepository

interface AppContainer {
    val flightRepository: FlightRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val flightRepository: FlightRepository by lazy { OfflineFlightRepository(FlightDatabase.getDatabase(context).getDao()) }
}