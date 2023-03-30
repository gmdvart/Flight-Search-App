package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FlightStatusScreen(
    modifier: Modifier = Modifier,
    statusText: String
) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            text = statusText,
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}