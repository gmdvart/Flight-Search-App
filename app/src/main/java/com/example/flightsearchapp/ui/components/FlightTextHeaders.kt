package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FlightMainHeader(
    modifier: Modifier = Modifier,
    text: String = "Header"
) {
    Text(
        text = text,
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.Start),
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun FlightAirportIataHeader(
    modifier: Modifier = Modifier,
    airportIata: String
) {
    Text(
        text = airportIata,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun FlightAirportNameHeader(
    modifier: Modifier = Modifier,
    airportFullName: String
) {
    Text(
        text = airportFullName,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall
    )
}

@Composable
fun FlightInfoHeader(
    modifier: Modifier = Modifier,
    info: String
) {
    Text(
        text = info,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold
    )
}