package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
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
        Text(text = airportIata)
        Spacer(modifier = modifier.weight(1f))
        Text(text = airportFullName)
    }
}
@Composable
fun FavoriteAirportItem(
    modifier: Modifier = Modifier,
    favoriteItem: FavoriteItem,
    onClick: (Int) -> Unit = {},
    favoriteIndicatorColor: Color = Color.Red
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = stringResource(id = R.string.flight_depart))
            FavoriteAirportInformation(
                airportIata = favoriteItem.departureCode,
                airportFullName = favoriteItem.departureName
            )
            Text(text = stringResource(id = R.string.flight_arrive))
            FavoriteAirportInformation(
                airportIata = favoriteItem.destinationCode,
                airportFullName = favoriteItem.destinationName
            )
        }
        IconButton(
            onClick = { onClick(favoriteItem.favoriteId) },
            content = {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = stringResource(id = R.string.favorite),
                    tint = favoriteIndicatorColor
                )
            }
        )
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun FavoriteAirportItemPreview() {
    FlightSearchAppTheme {
        FavoriteAirportItem(
            favoriteItem = FavoriteItem(
                favoriteId = 0,
                departureCode = "IAS",
                departureName = "International Airport of Some City",
                destinationCode = "AST",
                destinationName = "Airport of Some Town"
            ),
        )
    }
}