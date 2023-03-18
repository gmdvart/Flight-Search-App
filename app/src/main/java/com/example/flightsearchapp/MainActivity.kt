package com.example.flightsearchapp

import android.os.Bundle
import android.provider.Settings.Global
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.flightsearchapp.data.database.FlightDatabase
import com.example.flightsearchapp.ui.components.FlightHomeScreen
import com.example.flightsearchapp.ui.theme.FlightSearchAppTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlightSearchAppTheme {
                FlightHomeScreen()
            }
        }
    }
}