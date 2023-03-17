package com.example.flightsearchapp.ui.viewModels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearchapp.FlightApplication

object ViewModelFactoryProvider {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            HomeViewModel(getApplication().container.flightRepository)
        }

        initializer {
            SearchViewModel(getApplication().container.flightRepository)
        }
    }
}

fun CreationExtras.getApplication(): FlightApplication = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightApplication)