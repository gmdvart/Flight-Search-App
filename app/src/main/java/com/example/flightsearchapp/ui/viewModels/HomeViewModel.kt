package com.example.flightsearchapp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearchapp.data.database.Favorite
import com.example.flightsearchapp.data.repository.FlightRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val flightRepository: FlightRepository) : ViewModel() {
    companion object {
        val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> = flightRepository.getAllFavorites()
        .map { HomeUiState(favorites = it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            HomeUiState(favorites = listOf())
        )

    suspend fun removeFavorite(favorite: Favorite) = flightRepository.deleteFavorite(favorite)

    suspend fun addToFavorite(favorite: Favorite) = flightRepository.insertFavorite(favorite)
}