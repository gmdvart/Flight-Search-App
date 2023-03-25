package com.example.flightsearchapp.ui.viewModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.flightsearchapp.data.database.Airport
import com.example.flightsearchapp.data.database.Favorite

data class SearchUiState(
    val searchText: String = "",
    val isSearching: Boolean = false,
    val results: List<Airport> = listOf(),
    val resultsBySelected: List<Pair<Airport, Airport>> = listOf(),
    val favorites: List<Favorite> = listOf()
)

fun SearchUiState.isSearchStringEmpty(): Boolean = searchText.isEmpty()

fun SearchUiState.isResultsNotFound(): Boolean = searchText.isEmpty() || searchText.isNotEmpty() && results.isEmpty()

fun SearchUiState.isResultSelected(): Boolean = resultsBySelected.isNotEmpty()

fun flightToFavorite(pair: Pair<Airport, Airport>): Favorite = Favorite(
    id = (pair.first.id.toString() + pair.second.id.toString()).toInt(),
    departureCode = pair.first.iataCode,
    destinationCode = pair.second.iataCode
)

data class FavoriteItem(
    val departureCode: String,
    val destinationCode: String,
    val departureName: String,
    val destinationName: String
)