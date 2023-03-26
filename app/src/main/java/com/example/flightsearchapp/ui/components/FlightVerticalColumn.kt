package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightsearchapp.data.database.Airport
import com.example.flightsearchapp.ui.viewModels.FavoriteItem

@Composable
fun FlightVerticalColumn(
    modifier: Modifier = Modifier,
    airports: List<Airport>,
    onItemClick: (Airport) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = airports) {
            FlightAirportItem(
                airport = it,
                onClick = onItemClick
            )
            Divider(modifier = Modifier.padding(4.dp).fillMaxWidth())
        }
    }
}

@Composable
fun FlightVerticalPairColumn(
    modifier: Modifier = Modifier,
    airportPairs: List<Pair<Airport, Airport>>,
    onItemClick: (Pair<Airport, Airport>) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = airportPairs) { airportPair ->
            FlightAirportPairItem(
                pair = airportPair,
                onClick = { onItemClick(it) }
            )
            Divider(modifier = Modifier.padding(4.dp).fillMaxWidth())
        }
    }
}

@Composable
fun FavoriteVerticalColumn(
    modifier: Modifier = Modifier,
    favoriteItems: List<FavoriteItem>,
    onItemClick: (Int) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = favoriteItems) { favorite ->
            FavoriteAirportItem(
                favoriteItem = favorite,
                onClick = { onItemClick(favorite.favoriteId) }
            )
            Divider(modifier = Modifier.padding(4.dp).fillMaxWidth())
        }
    }
}