package com.example.flightsearchapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database([Airport::class, Favorite::class], version = 1)
abstract class FlightDatabase : RoomDatabase() {
    abstract fun getDao(): FlightDao

    companion object {
        @Volatile
        private var INSTANCE: FlightDatabase? = null

        fun getDatabase(context: Context): FlightDatabase = INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(context, FlightDatabase::class.java, "flight_database")
                .createFromAsset("database/flight_search.db")
                .fallbackToDestructiveMigration()
                .build()
                .also { INSTANCE = it }
        }
    }
}