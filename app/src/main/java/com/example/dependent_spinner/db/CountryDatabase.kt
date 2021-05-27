package com.example.dependent_spinner.db

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dependent_spinner.data.City
import com.example.dependent_spinner.data.Country
import com.example.dependent_spinner.data.State

@Database(
    entities = [Country::class,City::class,State::class],
    version = 1,
    exportSchema = true
)
abstract class CountryDatabase :RoomDatabase(){

    abstract fun countryDao():CountryDao
    abstract fun cityDao():CityDao
    abstract fun stateDao():StateDao

    companion object{
        @Volatile private var instance:CountryDatabase? = null
        private val lock= Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context)  = Room.databaseBuilder(
            context.applicationContext,
            CountryDatabase::class.java,
            "country"
        ).build()
    }
}