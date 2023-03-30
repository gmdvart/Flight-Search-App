package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import com.example.flightsearchapp.ui.viewModel.FavoriteItem

@Composable
fun FlightAirportItem(
    modifier: Modifier = Modifier,
    isInColumn: Boolean = false,
    airport: Airport? = null,
    airportIata: String,
    airportName: String,
    isClickEnabled: Boolean = true,
    onClick: (Airport) -> Unit = {}
) {
    val contentModifier = if (isInColumn) Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        else Modifier.padding(horizontal = 8.dp, vertical = 12.dp)

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(size = if (isInColumn) 16.dp else 8.dp)
    ) {
        Row(modifier = contentModifier
            .clickable(enabled = isClickEnabled) { if (airport != null) onClick(airport) }
            .fillMaxWidth()
        ) {
            if (!isInColumn) {
                FlightAirportIataHeader(airportIata = airportIata)
                Spacer(modifier = modifier.weight(1f))
                FlightAirportNameHeader(airportFullName = airportName)
            } else {
                Column {
                    FlightAirportIataHeader(airportIata = airportIata)
                    FlightAirportNameHeader(airportFullName = airportName)
                }
            }
        }
    }
}

@Composable
fun FlightFavoriteItem(
    modifier: Modifier = Modifier,
    favoriteItem: FavoriteItem,
    onHeartClick: (FavoriteItem) -> Unit = {},
    tint: Color
) {
    Card(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp, top = 8.dp)
            ) {
                FlightInfoHeader(info = stringResource(id = R.string.flight_depart))
                FlightAirportItem(
                    airportIata = favoriteItem.departureCode,
                    airportName = favoriteItem.departureName,
                    isClickEnabled = false,
                    isInColumn = true
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                )
                FlightInfoHeader(info = stringResource(id = R.string.flight_arrive))
                FlightAirportItem(
                    airportIata = favoriteItem.destinationCode,
                    airportName = favoriteItem.destinationName,
                    isClickEnabled = false,
                    isInColumn = true
                )
            }
            IconButton(
                onClick = { onHeartClick(favoriteItem) },
                modifier = Modifier.padding(10.dp).size(48.dp),
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Favorite,
                        contentDescription = stringResource(id = R.string.favorites),
                        tint = tint
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun FlightAirportItemLightPreview() {
    FlightSearchAppTheme(useDarkTheme = false) {
        val mockAirport = Airport(0, "LOL", "Lead of Laugh", 999)
        FlightAirportItem(
            airport = mockAirport,
            airportIata = mockAirport.iataCode,
            airportName = mockAirport.name,
            isClickEnabled = false
        )
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun FlightAirportItemDarkPreview() {
    FlightSearchAppTheme(useDarkTheme = true) {
        val mockAirport = Airport(0, "LOL", "Lead of Laugh", 999)
        FlightAirportItem(
            airport = mockAirport,
            airportIata = mockAirport.iataCode,
            airportName = mockAirport.name,
            isClickEnabled = false
        )
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun FlightFavoriteItemLightPreview() {
    FlightSearchAppTheme(useDarkTheme = false) {
        FlightFavoriteItem(
            favoriteItem = FavoriteItem(
                id = 0,
                departureCode = "IAS",
                departureName = "International Airport of Some City",
                destinationCode = "AST",
                destinationName = "Airport of Some Town"
            ),
            tint = Color.Red
        )
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun FlightFavoriteItemDarkPreview() {
    FlightSearchAppTheme(useDarkTheme = true) {
        FlightFavoriteItem(
            favoriteItem = FavoriteItem(
                id = 0,
                departureCode = "IAS",
                departureName = "International Airport of Some City",
                destinationCode = "AST",
                destinationName = "Airport of Some Town"
            ),
            tint = Color.Gray
        )
    }
}