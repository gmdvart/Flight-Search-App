package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flightsearchapp.ui.theme.FlightSearchAppTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize()) {
        Column {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    FlightSearchAppTheme {
        HomeScreen()
    }
}

