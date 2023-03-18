package com.example.flightsearchapp.ui.viewModels

import androidx.room.Entity
import com.example.flightsearchapp.data.database.Airport
import com.example.flightsearchapp.data.database.Favorite

data class SearchUiState(
    val searchText: String = "",
    val results: List<Airport> = listOf(),
    val resultsBySelected: List<Pair<Airport, Airport>> = listOf()
)

fun SearchUiState.isSearchStringEmpty(): Boolean = searchText.isEmpty()


fun SearchUiState.isResultsNotFound(): Boolean = searchText.isNotEmpty() && results.isEmpty()

fun SearchUiState.flightToFavorite(index: Int): Favorite = Favorite(
    id = (resultsBySelected[index].first.id.toString() + resultsBySelected[index].second.id.toString()).toInt(),
    departureCode = resultsBySelected[index].first.iataCode,
    destinationCode = resultsBySelected[index].second.iataCode
)