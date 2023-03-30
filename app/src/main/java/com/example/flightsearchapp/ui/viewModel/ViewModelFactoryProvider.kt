package com.example.flightsearchapp.ui.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearchapp.FlightApplication

object ViewModelFactoryProvider {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            SearchViewModel(
                flightRepository = getApplication().container.flightRepository,
                userPreferenceRepository = getApplication().container.userPreferenceRepository
            )
        }
    }
}

fun CreationExtras.getApplication(): FlightApplication = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightApplication)