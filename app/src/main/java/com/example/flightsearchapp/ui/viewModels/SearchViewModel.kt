package com.example.flightsearchapp.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearchapp.data.database.Airport
import com.example.flightsearchapp.data.database.Favorite
import com.example.flightsearchapp.data.database.toFavoriteItem
import com.example.flightsearchapp.data.repository.FlightRepository
import com.example.flightsearchapp.utlis.generatePairsToElement
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
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
                val results = flightRepository.getAirportsByIataOrName(searchText).first()
                searchUiState = searchUiState.copy(results = results)
            }
        } else {
            searchUiState = searchUiState.copy(results = listOf())
        }
    }

    fun updateSearchUiState(newSearchUiState: SearchUiState) {
        searchUiState = newSearchUiState
    }

    fun selectAirport(airport: Airport) = viewModelScope.launch {
        val allAirports = flightRepository.getAllAirports().first()
        val pairs = async { allAirports.generatePairsToElement(airport) }

        searchUiState = searchUiState.copy(resultsBySelected = pairs.await())
    }

    suspend fun addPairToFavorite(pair: Pair<Airport, Airport>) {
        val favorite = flightToFavorite(pair)
        flightRepository.insertFavorite(
            favorite = favorite
        )
    }

    suspend fun removeFavorite(favorite: Favorite) {
        flightRepository.deleteFavorite(favorite = favorite)
    }

    suspend fun getAirport(iataCode: String): Airport = flightRepository.getAirportByIata(iataCode).first()

    fun refreshFavorites() {
        viewModelScope.launch {
            searchUiState = searchUiState.copy(
                favorites = flightRepository.getAllFavorites()
                    .filterNotNull()
                    .first()
                    .map {
                        it.toFavoriteItem(
                            departureName = getAirport(it.departureCode).name,
                            destinationName = getAirport(it.destinationCode).name
                        )
                    }
            )
        }
    }

    init {
        refreshFavorites()
    }
}