package com.example.flightsearchapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flightsearchapp.data.database.Airport
import com.example.flightsearchapp.ui.theme.FlightSearchAppTheme

@Composable
fun FlightItem(modifier: Modifier = Modifier, airport: Airport) {
    Row(modifier = modifier.padding(4.dp).fillMaxWidth()) {
        Text(text = airport.iataCode)
        Spacer(modifier = modifier.weight(1f))
        Text(text = airport.name)
    }
}

@Preview(showBackground = true)
@Composable
fun FlightItemPreview() {
    FlightSearchAppTheme {
        FlightItem(airport = Airport(0, "LOL", "Lead of Laugh", 999))
    }
}