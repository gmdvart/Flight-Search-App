package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flightsearchapp.R
import com.example.flightsearchapp.data.database.Airport
import com.example.flightsearchapp.ui.theme.FlightSearchAppTheme

@Composable
fun FlightAirportItem(
    modifier: Modifier = Modifier,
    airport: Airport,
    isClickEnabled: Boolean = true,
    onClick: (Airport) -> Unit = {}
) {
    Row(modifier = modifier
        .padding(4.dp)
        .clickable(enabled = isClickEnabled) { onClick(airport) }
        .fillMaxWidth()
    ) {
        FlightAirportIataHeader(airportIata = airport.iataCode)
        Spacer(modifier = modifier.weight(1f))
        FlightAirportNameHeader(airportFullName = airport.name)
    }
}

@Composable
fun FlightAirportPairItem(
    modifier: Modifier = Modifier,
    pair: Pair<Airport, Airport>,
    onClick: (Pair<Airport, Airport>) -> Unit = {},
    favoriteIndicatorColor: Color = Color.Gray
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            FlightInfoHeader(info = stringResource(id = R.string.flight_depart))
            FlightAirportItem(airport = pair.first, isClickEnabled = false)
            FlightInfoHeader(info = stringResource(id = R.string.flight_arrive))
            FlightAirportItem(airport = pair.second, isClickEnabled = false)
        }
        IconButton(
            onClick = { onClick(pair) },
            content = {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = stringResource(id = R.string.favorites),
                    tint = favoriteIndicatorColor
                )
            }
        )
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun FlightAirportItemPreview() {
    FlightSearchAppTheme {
        FlightAirportItem(airport = Airport(0, "LOL", "Lead of Laugh", 999), isClickEnabled = false)
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