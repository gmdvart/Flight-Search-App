package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.flightsearchapp.ui.viewModels.SearchUiState

@Composable
fun FlightSearchTextField(
    modifier: Modifier = Modifier,
    focusManager: FocusManager,
    searchUiState: SearchUiState,
    onValueChange: (String) -> Unit = {}
) {
    TextField(
        value = searchUiState.searchText,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        singleLine = true
    )
}