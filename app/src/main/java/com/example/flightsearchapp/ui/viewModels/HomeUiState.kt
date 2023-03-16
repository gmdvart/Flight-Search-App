package com.example.flightsearchapp.ui.viewModels

import com.example.flightsearchapp.data.database.Favorite

data class HomeUiState(val favorites: List<Favorite>)

fun HomeUiState.isFavoritesEmpty(): Boolean = favorites.isEmpty()