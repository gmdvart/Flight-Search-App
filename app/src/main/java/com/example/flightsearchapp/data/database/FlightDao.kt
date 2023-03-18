package com.example.flightsearchapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {
    @Insert(entity = Favorite::class)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete(entity = Favorite::class)
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM airport WHERE iata_code LIKE :searchString || '%' OR name LIKE :searchString || '%'")
    fun getAirportByIataOrName(searchString: String): Flow<List<Airport>>

    @Query("SELECT * FROM airport WHERE id = :id")
    fun getAirportById(id: Int): Flow<Airport>
}