package com.example.flightsearchapp.ui.viewModels

import androidx.room.Entity
import com.example.flightsearchapp.data.database.Airport
import com.example.flightsearchapp.data.database.Favorite

data class SearchUiState(
    val searchText: String = "",
    val results: List<Airport> = listOf(),
    val selectedResults: List<Pair<Airport, Airport>> = listOf()
)

fun SearchUiState.isSearchStringEmpty(): Boolean = searchText.isEmpty()

fun SearchUiState.flightToFavorite(index: Int): Favorite = Favorite(
    departureCode = selectedResults[index].first.iataCode,
    destinationCode = selectedResults[index].second.iataCode
)