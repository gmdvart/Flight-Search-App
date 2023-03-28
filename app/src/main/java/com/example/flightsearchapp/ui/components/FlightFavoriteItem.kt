package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flightsearchapp.R
import com.example.flightsearchapp.ui.theme.FlightSearchAppTheme
import com.example.flightsearchapp.ui.viewModels.FavoriteItem

@Composable
fun FavoriteAirportInformation(
    modifier: Modifier = Modifier,
    airportIata: String,
    airportFullName: String
) {
    Row(modifier = modifier
        .padding(4.dp)
        .fillMaxWidth()
    ) {
        FlightAirportIataHeader(airportIata = airportIata)
        Spacer(modifier = modifier.weight(1f))
        FlightAirportNameHeader(airportFullName = airportFullName)
    }
}
@Composable
fun FavoriteAirportItem(
    modifier: Modifier = Modifier,
    favoriteItem: FavoriteItem,

) {
    Column(modifier = modifier.padding(8.dp)) {
        FlightInfoHeader(info = stringResource(id = R.string.flight_depart))
        FavoriteAirportInformation(
            airportIata = favoriteItem.departureCode,
            airportFullName = favoriteItem.departureName
        )
        FlightInfoHeader(info = stringResource(id = R.string.flight_arrive))
        FavoriteAirportInformation(
            airportIata = favoriteItem.destinationCode,
            airportFullName = favoriteItem.destinationName
        )
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun FavoriteAirportItemPreview() {
    FlightSearchAppTheme {
        FavoriteAirportItem(
            favoriteItem = FavoriteItem(
                id = 0,
                departureCode = "IAS",
                departureName = "International Airport of Some City",
                destinationCode = "AST",
                destinationName = "Airport of Some Town"
            ),
        )
    }
}