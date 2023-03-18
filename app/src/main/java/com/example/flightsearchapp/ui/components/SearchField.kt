package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightsearchapp.ui.viewModels.SearchUiState

@Composable
fun FlightSearchTextField(
    modifier: Modifier = Modifier,
    searchUiState: SearchUiState,
    onValueChange: (String) -> Unit = {}
) {
    TextField(
        value = searchUiState.searchText,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        singleLine = true
    )
}