package com.example.flightsearchapp.ui.viewModels

import com.example.flightsearchapp.data.database.Airport
import com.example.flightsearchapp.data.database.Favorite

data class SearchUiState(
    val searchText: String = "",
    val isSearching: Boolean = false,
    val results: List<Airport> = listOf(),
    val resultsBySelected: List<FavoriteItem> = listOf(),
    val favorites: List<FavoriteItem> = listOf(),
    val favoritesId: List<Int> = listOf()
)

fun SearchUiState.isSearchStringEmpty(): Boolean = searchText.isEmpty()

fun SearchUiState.isResultsNotFound(): Boolean = searchText.isEmpty() || searchText.isNotEmpty() && results.isEmpty()

fun SearchUiState.isResultSelected(): Boolean = resultsBySelected.isNotEmpty()

data class FavoriteItem(
    val id: Int,
    val departureCode: String,
    val destinationCode: String,
    val departureName: String,
    val destinationName: String,
    var markedAsFavorite: Boolean = false
)


fun FavoriteItem.toFavorite(): Favorite = Favorite(
    id = id,
    departureCode = departureCode,
    destinationCode = destinationCode
)