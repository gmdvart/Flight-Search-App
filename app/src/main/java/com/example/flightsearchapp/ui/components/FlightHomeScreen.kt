package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearchapp.ui.theme.FlightSearchAppTheme
import com.example.flightsearchapp.ui.viewModels.SearchUiState
import com.example.flightsearchapp.ui.viewModels.SearchViewModel
import com.example.flightsearchapp.ui.viewModels.ViewModelFactoryProvider

@Composable
fun FlightHomeScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = viewModel(factory = ViewModelFactoryProvider.Factory)
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FlightSearchTextField(
            searchUiState = searchViewModel.searchUiState,
            onValueChange = searchViewModel::performSearch
        )
        ResultScreen(searchUiState = searchViewModel.searchUiState)
    }
}

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    searchUiState: SearchUiState
) {
    if (searchUiState.results.isNotEmpty()) DisplayResults(searchUiState = searchUiState)
    else ResultsNotFinded()
}

@Composable
fun DisplayResults(
    modifier: Modifier = Modifier,
    searchUiState: SearchUiState
) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(text = "Results found: ${searchUiState.results.size}")
    }
}

@Composable
fun ResultsNotFinded(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(text = "Find nothing.")
    }
}

@Preview(showBackground = true)
@Composable
fun FlightHomeScreenPreview() {
    FlightSearchAppTheme {
        FlightHomeScreen()
    }
}

