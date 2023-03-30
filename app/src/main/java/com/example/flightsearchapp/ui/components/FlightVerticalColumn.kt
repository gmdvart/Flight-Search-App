package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.flightsearchapp.data.database.Airport
import com.example.flightsearchapp.ui.viewModel.FavoriteItem

@Composable
fun FlightVerticalColumn(
    modifier: Modifier = Modifier,
    airports: List<Airport>,
    onItemClick: (Airport) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = airports) {
            FlightAirportItem(
                airport = it,
                modifier = Modifier.padding(4.dp),
                onClick = onItemClick,
                airportIata = it.iataCode,
                airportName = it.name
            )
        }
    }
}

@Composable
fun FlightFavoriteVerticalColumn(
    modifier: Modifier = Modifier,
    favoriteItems: List<FavoriteItem>,
    onItemClick: (FavoriteItem) -> Unit,
    selectedItemsState: MutableMap<Int, Boolean> = mutableMapOf()
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(
            items = favoriteItems,
            key = { favoriteItem -> favoriteItem.id }
        ) { favoriteItem ->
            FlightFavoriteItem(
                favoriteItem = favoriteItem,
                modifier = Modifier.padding(4.dp),
                onHeartClick = {
                    selectedItemsState[favoriteItem.id] = selectedItemsState[favoriteItem.id] != true
                    onItemClick(favoriteItem)
                },
                tint = if (selectedItemsState[favoriteItem.id] == true)
                    Color.Red else Color.Gray
            )
        }
    }
}