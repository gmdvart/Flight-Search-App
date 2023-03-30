package com.example.flightsearchapp.ui.components

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearchapp.ui.theme.FlightSearchAppTheme
import com.example.flightsearchapp.R
import com.example.flightsearchapp.data.database.Airport
import com.example.flightsearchapp.ui.viewModel.*
import kotlinx.coroutines.launch

@Composable
fun FlightHomeScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = viewModel(factory = ViewModelFactoryProvider.Factory)
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FlightSearchTextField(
            focusManager = focusManager,
            searchUiState = searchViewModel.searchUiState,
            onValueChange = {
                searchViewModel.performSearch(it)
                searchViewModel.isUserSearching(isSearching = true)
            }
        )
        FlightMainHeader(
            text = if (searchViewModel.searchUiState.isSearching)
                stringResource(id = R.string.results) else stringResource(id = R.string.favorites)
        )
        Divider(modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
        ResultScreen(
            searchViewModel = searchViewModel,
            onAirportResultClick = {
                searchViewModel.selectAirport(it)
                focusManager.clearFocus()
            }
        )
    }

    BackHandler(
        enabled = true,
        onBack = {
            focusManager.clearFocus()
            searchViewModel.isUserSearching(isSearching = false)
        }
    )
}

@Composable
fun ResultScreen(
    searchViewModel: SearchViewModel,
    onAirportResultClick: (Airport) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val searchUiState = searchViewModel.searchUiState

    if (searchUiState.isBusy) {
        FlightStatusScreen(statusText = stringResource(id = R.string.loading))
        return
    }

    val selectedItems: MutableMap<Int, Boolean> = remember {
        searchUiState.favorites.associate {
            it.id to it.markedAsFavorite
        }.toMutableMap()
    }

    if (searchUiState.isSearching) {
        if (searchUiState.isResultSelected()) {
            FlightFavoriteVerticalColumn(
                favoriteItems = searchUiState.resultsBySelected,
                onItemClick = {
                    coroutineScope.launch { searchViewModel.markFlightAsFavorite(it) }
                },
                selectedItemsState = selectedItems
            )
        } else if (!searchUiState.isResultsNotFound()) {
            FlightVerticalColumn(
                airports = searchUiState.results,
                onItemClick = onAirportResultClick
            )
        } else {
            FlightStatusScreen(statusText = stringResource(id = R.string.found_nothing))
        }
    } else {
        FavoritesScreen(
            favoriteItems = searchUiState.favorites,
            onItemCLick = {
                coroutineScope.launch { searchViewModel.removeFavorite(it) }
            },
            selectedItemsState = selectedItems
        )
    }
}

@Composable
fun FavoritesScreen(
    favoriteItems: List<FavoriteItem>,
    onItemCLick: (FavoriteItem) -> Unit,
    selectedItemsState: MutableMap<Int, Boolean>
) {
        if (favoriteItems.isEmpty()) {
            FlightStatusScreen(statusText = stringResource(id = R.string.favourites_are_empty))
        } else {
            FlightFavoriteVerticalColumn(
                favoriteItems = favoriteItems,
                onItemClick = onItemCLick,
                selectedItemsState = selectedItemsState
            )
        }
}

@Preview(showBackground = true)
@Composable
fun FlightHomeScreenPreview() {
    FlightSearchAppTheme {
        FlightHomeScreen()
    }
}
