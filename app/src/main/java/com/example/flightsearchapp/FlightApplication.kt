package com.example.flightsearchapp

import android.app.Application
import com.example.flightsearchapp.data.container.AppContainer
import com.example.flightsearchapp.data.container.AppDataContainer

class FlightApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}