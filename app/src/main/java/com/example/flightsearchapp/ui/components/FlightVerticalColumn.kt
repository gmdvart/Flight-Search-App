package com.example.flightsearchapp.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.flightsearchapp.R
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
    onItemClick: (Pair<Airport, Airport>) -> Unit,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = airportPairs) { airportPair ->
            FlightAirportPairItem(
                pair = airportPair,
                onClick = { onItemClick(it) },
            )
            Divider(modifier = Modifier.padding(4.dp).fillMaxWidth())
        }
    }
}

@Composable
fun FavoriteVerticalColumn(
    modifier: Modifier = Modifier,
    favoriteItems: List<FavoriteItem>,
    onItemClick: (FavoriteItem) -> Unit,
) {
    val selectedItems: MutableMap<Int, Boolean> = remember {
        favoriteItems.associate { it.id to it.markedAsFavorite }.toMutableMap()
    }

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = favoriteItems, key = { favoriteItem -> favoriteItem.id }) { favorite ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                FavoriteAirportItem(
                    modifier = Modifier.weight(1f),
                    favoriteItem = favorite,
                )
                IconButton(
                    onClick = {
                        selectedItems[favorite.id] = selectedItems[favorite.id] != true
                        onItemClick(favorite)
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Outlined.Favorite,
                            contentDescription = stringResource(id = R.string.favorite),
                            tint = if (selectedItems[favorite.id] == true)
                                Color.Red else Color.Gray
                        )
                    }
                )
            }
            Divider(modifier = Modifier.padding(4.dp).fillMaxWidth())
        }
    }
}