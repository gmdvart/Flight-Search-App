package com.example.flightsearchapp.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearchapp.data.database.Airport
import com.example.flightsearchapp.data.database.Favorite
import com.example.flightsearchapp.data.database.createFavoriteIdWith
import com.example.flightsearchapp.data.database.toFavoriteItem
import com.example.flightsearchapp.data.repository.FlightRepository
import com.example.flightsearchapp.data.repository.UserPreferenceRepository
import com.example.flightsearchapp.utlis.generatePairsToElement
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

class SearchViewModel(
    private val flightRepository: FlightRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) : ViewModel() {
    var searchUiState by mutableStateOf(SearchUiState())
        private set

    fun isUserSearching(isSearching: Boolean) {
        searchUiState = searchUiState.copy(isSearching = isSearching)
    }

    fun performSearch(searchText: String) {
        searchUiState = searchUiState.copy(
            searchText = searchText,
            resultsBySelected = listOf(),
            isBusy = true
        )
        viewModelScope.launch {
            userPreferenceRepository.saveSearchStringPreference(searchText)

            searchUiState = searchUiState.copy(
                results = if (!searchUiState.isSearchStringEmpty())
                flightRepository.getAirportsByIataOrName(searchText).first() else listOf(),
                isBusy = false
            )
        }
    }

    fun selectAirport(airport: Airport) = viewModelScope.launch {
        searchUiState = searchUiState.copy(isBusy = true)

        val allAirports = flightRepository.getAllAirports().first()
        val pairs = allAirports.generatePairsToElement(airport)
        val potentialFavorites = toPotentialFavorites(pairs)

        searchUiState = searchUiState.copy(resultsBySelected = potentialFavorites, isBusy = false)
    }

    private suspend fun getAirport(iataCode: String): Airport = flightRepository.getAirportByIata(iataCode).first()

    suspend fun markFlightAsFavorite(potentialFavoriteItem: FavoriteItem) {
        if (!flightRepository.isContainsFavorite(potentialFavoriteItem.id)) {
            flightRepository.insertFavorite(potentialFavoriteItem.toFavorite())
        } else {
            removeFavorite(potentialFavoriteItem)
        }
        refreshFavorites()
    }

    suspend fun removeFavorite(favoriteItem: FavoriteItem) {
        flightRepository.deleteFavorite(favoriteItem.toFavorite())
        refreshFavorites()
    }

    private fun refreshFavorites(isBusy: Boolean = false, isOnLaunch: Boolean = false) {
        viewModelScope.launch {
            if (isBusy) searchUiState = searchUiState.copy(isBusy = true)
            val searchText = if (isOnLaunch) userPreferenceRepository.searchStringValue.first() else searchUiState.searchText
            try {
                searchUiState = searchUiState.copy(
                    searchText = searchText,
                    favorites = flightRepository.getAllFavorites()
                        .filterNotNull()
                        .first()
                        .map {
                            it.toFavoriteItem(
                                departureName = getAirport(it.departureCode).name,
                                destinationName = getAirport(it.destinationCode).name,
                            )
                        },
                    isBusy = false
                )
            } catch (e: IOException) {
                throw  Exception(e.message)
            }
        }
    }

    private suspend fun toPotentialFavorites(pairs: List<Pair<Airport, Airport>>): List<FavoriteItem> {
        return pairs.map { pair ->
            val favoriteId = pair.first.createFavoriteIdWith(pair.second)
            FavoriteItem(
                id = favoriteId,
                departureCode = pair.first.iataCode,
                departureName = pair.first.name,
                destinationCode = pair.second.iataCode,
                destinationName = pair.second.name,
                markedAsFavorite = flightRepository.isContainsFavorite(favoriteId)
            )
        }
    }

    init {
        refreshFavorites(isBusy = true, isOnLaunch = true)
    }
}