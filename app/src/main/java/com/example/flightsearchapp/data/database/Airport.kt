package com.example.flightsearchapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey val id: Int,
    @ColumnInfo("iata_code") val iataCode: String,
    val name: String,
    val passengers: Int
)
