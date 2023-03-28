package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FlightHeader(
    modifier: Modifier = Modifier,
    text: String = "Header"
) {
    Text(
        text = text,
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.Start),
        style = MaterialTheme.typography.h6,
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
        style = MaterialTheme.typography.body2
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
        style = MaterialTheme.typography.caption
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
        style = MaterialTheme.typography.body2,
        fontWeight = FontWeight.Bold
    )
}