package com.example.flightsearchapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.flightsearchapp.ui.viewModel.FavoriteItem

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey val id: Int,
    @ColumnInfo("departure_code") val departureCode: String,
    @ColumnInfo("destination_code") val destinationCode: String
)

fun Favorite.toFavoriteItem(departureName: String, destinationName: String) = FavoriteItem(
    id = id,
    departureCode = departureCode,
    departureName = departureName,
    destinationCode = destinationCode,
    destinationName = destinationName,
    markedAsFavorite = true
)
