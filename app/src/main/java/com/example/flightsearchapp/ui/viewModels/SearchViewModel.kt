package com.example.flightsearchapp.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearchapp.data.repository.FlightRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SearchViewModel(
    private val flightRepository: FlightRepository
) : ViewModel() {
    companion object {
        val TIMEOUT_MILLS = 5_000L
    }

    var searchUiState by mutableStateOf(SearchUiState())
        private set

    fun performSearch(searchText: String) {
        viewModelScope.launch {
            val results = flightRepository.getAirportByIataOrName(searchText).first()
            searchUiState = searchUiState.copy(results = results)
        }
    }
}