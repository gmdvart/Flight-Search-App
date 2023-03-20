package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flightsearchapp.data.database.Airport
import com.example.flightsearchapp.ui.theme.FlightSearchAppTheme

@Composable
fun FlightAirportItem(
    modifier: Modifier = Modifier,
    airport: Airport,
    enabled: Boolean = true,
    onClick: (Airport) -> Unit = {}
) {
    Row(modifier = modifier
        .padding(4.dp)
        .fillMaxWidth()
        .clickable(enabled = enabled) { onClick(airport) }
    ) {
        Text(text = airport.iataCode)
        Spacer(modifier = modifier.weight(1f))
        Text(text = airport.name)
    }
}

@Composable
fun FlightAirportPairItem(
    modifier: Modifier = Modifier,
    pair: Pair<Airport, Airport>,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Column(modifier = modifier
        .clickable(enabled = enabled) { onClick() }
        .padding(4.dp)
    ) {
        Column {
            Text(text = "Depart")
            FlightAirportItem(airport = pair.first, enabled = false)
        }
        Column {
            Text(text = "Arrive")
            FlightAirportItem(airport = pair.second, enabled = false)
        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun FlightAirportItemPreview() {
    FlightSearchAppTheme {
        FlightAirportItem(airport = Airport(0, "LOL", "Lead of Laugh", 999))
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun FlightAirportPairItemPreview() {
    val firstAirport = Airport(0, "LOL", "Lead of Laugh", 999)
    val secondAirport = Airport(1, "SNA", "Saint Name Airport", 666)
    val mockData = Pair(firstAirport, secondAirport)

    FlightSearchAppTheme {
        FlightAirportPairItem(pair = mockData)
    }
}