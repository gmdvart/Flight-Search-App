package com.example.flightsearchapp.ui.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearchapp.data.database.Airport
import com.example.flightsearchapp.data.repository.FlightRepository
import com.example.flightsearchapp.utlis.generatePairsToElement
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SearchViewModel(
    val flightRepository: FlightRepository
) : ViewModel() {
    companion object {
        val TIMEOUT_MILLS = 5_000L
    }

    var searchUiState by mutableStateOf(SearchUiState())
        private set

    fun performSearch(searchText: String) {
        searchUiState = searchUiState.copy(searchText = searchText, resultsBySelected = listOf())

        if (!searchUiState.isSearchStringEmpty()) {
            // Performing search
            viewModelScope.launch {
                val results = flightRepository.getAirportByIataOrName(searchText).first()
                searchUiState = searchUiState.copy(results = results)
            }
        } else {
            searchUiState = searchUiState.copy(results = listOf())
        }
    }

    fun selectAirport(airport: Airport) = viewModelScope.launch {
        val allAirports = flightRepository.getAllAirports().first()
        val pairs = async { allAirports.generatePairsToElement(airport) }

        searchUiState = searchUiState.copy(resultsBySelected = pairs.await())
    }
}