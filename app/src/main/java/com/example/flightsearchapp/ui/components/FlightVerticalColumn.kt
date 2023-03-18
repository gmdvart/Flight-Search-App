package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.flightsearchapp.data.database.Airport

@Composable
fun FlightVerticalColumn(
    modifier: Modifier = Modifier,
    airports: List<Airport>
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = airports) {
            FlightAirportItem(airport = it)
        }
    }
}