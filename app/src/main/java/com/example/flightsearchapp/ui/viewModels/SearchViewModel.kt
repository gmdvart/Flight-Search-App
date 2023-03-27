package com.example.flightsearchapp.ui.viewModels

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
import com.example.flightsearchapp.utlis.generatePairsToElement
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

    fun updateSearchUiState(newSearchUiState: SearchUiState) {
        searchUiState = newSearchUiState
    }

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

    fun selectAirport(airport: Airport) = viewModelScope.launch {
        val allAirports = flightRepository.getAllAirports().first()
        val pairs = allAirports.generatePairsToElement(airport)
        val potentialFavorites = toPotentialFavorites(pairs)

        searchUiState = searchUiState.copy(resultsBySelected = potentialFavorites)
    }

    suspend fun getAirport(iataCode: String): Airport = flightRepository.getAirportByIata(iataCode).first()

    suspend fun markFlightAsFavorite(potentialFavoriteItem: FavoriteItem) {
        if (!flightRepository.isContainsFavorite(potentialFavoriteItem.id)) {
            flightRepository.insertFavorite(potentialFavoriteItem.toFavorite())
        } else {
            removeFavorite(potentialFavoriteItem)
        }
        refreshFavorites()
    }

    suspend fun addPairToFavorite(pair: Pair<Airport, Airport>) {
        val favoriteId = pair.first.createFavoriteIdWith(pair.second)

        if (flightRepository.isContainsFavorite(favoriteId)) {
            val favorite = flightToFavorite(pair)
            flightRepository.insertFavorite(
                favorite = favorite
            )
        } else {
//            removeFavorite(favoriteId)
        }

        refreshFavorites()
    }

    suspend fun removeFavorite(favoriteItem: FavoriteItem) {
        flightRepository.deleteFavorite(favoriteItem.toFavorite())
        refreshFavorites()
    }

    fun refreshFavorites() {
        viewModelScope.launch {
            searchUiState = searchUiState.copy(
                favorites = flightRepository.getAllFavorites()
                    .filterNotNull()
                    .first()
                    .map {
                        it.toFavoriteItem(
                            departureName = getAirport(it.departureCode).name,
                            destinationName = getAirport(it.destinationCode).name,
                        )
                    }
            )
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

    private fun flightToFavorite(pair: Pair<Airport, Airport>): Favorite = Favorite(
        id = pair.first.createFavoriteIdWith(pair.second),
        departureCode = pair.first.iataCode,
        destinationCode = pair.second.iataCode
    )

    init {
        refreshFavorites()
    }
}