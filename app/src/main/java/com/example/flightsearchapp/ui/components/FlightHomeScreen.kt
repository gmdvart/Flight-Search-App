package com.example.flightsearchapp.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearchapp.R
import com.example.flightsearchapp.data.database.Airport
import com.example.flightsearchapp.data.database.Favorite
import com.example.flightsearchapp.ui.theme.FlightSearchAppTheme
import com.example.flightsearchapp.ui.viewModels.*
import kotlinx.coroutines.launch

@Composable
fun FlightHomeScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = viewModel(factory = ViewModelFactoryProvider.Factory)
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FlightSearchTextField(
            focusManager = focusManager,
            searchUiState = searchViewModel.searchUiState,
            onValueChange = {
                searchViewModel.performSearch(it)
                searchViewModel.updateSearchUiState(searchViewModel.searchUiState.copy(isSearching = true))
            }
        )
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
            searchViewModel.updateSearchUiState(searchViewModel.searchUiState.copy(isSearching = false))
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

    if (searchUiState.isSearching) {
        if (searchUiState.isResultSelected()) {
            FlightVerticalPairColumn(
                modifier = Modifier.padding(4.dp),
                airportPairs = searchUiState.resultsBySelected,
                onItemClick = {
                    coroutineScope.launch { searchViewModel.addPairToFavorite(it) }
                }
            )
        } else if (!searchUiState.isResultsNotFound()) {
            FlightVerticalColumn(
                modifier = Modifier.padding(4.dp),
                airports = searchUiState.results,
                onItemClick = onAirportResultClick
            )
        } else {
            ResultsNotFound(modifier = Modifier.padding(4.dp))
        }
    } else {
        searchViewModel.refreshFavorites()
        FavoritesScreen(searchViewModel = searchViewModel)
    }
}

@Composable
fun ResultsNotFound(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.found_nothing),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (searchViewModel.searchUiState.favorites.isEmpty()) {
            Text(
                text = stringResource(id = R.string.favourites_are_empty),
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
//            FlightVerticalPairColumn()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlightHomeScreenPreview() {
    FlightSearchAppTheme {
        FlightHomeScreen()
    }
}

