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

    @Query("DELETE FROM favorite WHERE id = :id")
    suspend fun deleteFavoriteById(id: Int)

    @Query("SELECT * FROM favorite")
    fun getAllFavorites(): Flow<List<Favorite>>

    /** Method above is used only for testing! **/
    @Insert(entity = Airport::class)
    suspend fun insertAirport(airport: Airport)

    @Query("SELECT * FROM airport WHERE iata_code LIKE :searchString || '%' OR name LIKE :searchString || '%'")
    fun getAirportsByIataOrName(searchString: String): Flow<List<Airport>>

    @Query("SELECT * FROM airport WHERE id = :id")
    fun getAirportById(id: Int): Flow<Airport>

    @Query("SELECT * FROM airport WHERE iata_code = :iataCode")
    fun getAirportByIata(iataCode: String): Flow<Airport>

    @Query("SELECT * FROM airport")
    fun getAllAirports(): Flow<List<Airport>>
}
