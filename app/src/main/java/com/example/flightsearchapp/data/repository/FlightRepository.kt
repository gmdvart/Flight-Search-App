package com.example.flightsearchapp.data.repository

import com.example.flightsearchapp.data.database.Airport
import com.example.flightsearchapp.data.database.Favorite
import com.example.flightsearchapp.data.database.FlightDao
import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    suspend fun insertFavorite(favorite: Favorite)

    suspend fun deleteFavoriteById(id: Int)

    fun getAllFavorites(): Flow<List<Favorite>>

    fun getAllAirports(): Flow<List<Airport>>

    fun getAirportsByIataOrName(searchString: String): Flow<List<Airport>>

    fun getAirportById(id: Int): Flow<Airport>

    fun getAirportById(iataCode: String): Flow<Airport>
}

class OfflineFlightRepository(private val flightDao: FlightDao) : FlightRepository {
    override suspend fun insertFavorite(favorite: Favorite) = flightDao.insertFavorite(favorite)

    override suspend fun deleteFavoriteById(id: Int) = flightDao.deleteFavoriteById(id)

    override fun getAllFavorites(): Flow<List<Favorite>> = flightDao.getAllFavorites()

    override fun getAllAirports(): Flow<List<Airport>> = flightDao.getAllAirports()

    override fun getAirportsByIataOrName(searchString: String): Flow<List<Airport>> = flightDao.getAirportsByIataOrName(searchString)

    override fun getAirportById(id: Int): Flow<Airport> = flightDao.getAirportById(id)

    override fun getAirportById(iataCode: String): Flow<Airport> = flightDao.getAirportByIata(iataCode)
}