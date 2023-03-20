package com.example.flightsearchapp.ui.components

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearchapp.data.database.Airport
import com.example.flightsearchapp.ui.theme.FlightSearchAppTheme
import com.example.flightsearchapp.ui.viewModels.*

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
            onValueChange = searchViewModel::performSearch
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
        }
    )
}

@Composable
fun ResultScreen(
    searchViewModel: SearchViewModel,
    onAirportResultClick: (Airport) -> Unit
) {
    val searchUiState = searchViewModel.searchUiState

    if (searchUiState.isResultSelected()) {
      FlightVerticalPairColumn(
          modifier = Modifier.padding(4.dp),
          airportPairs = searchUiState.resultsBySelected,
          onItemClick = { }
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
}

@Composable
fun ResultsNotFound(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Find nothing.",
            modifier = Modifier.align(Alignment.Center)
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

