package com.example.dependent_spinner.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dependent_spinner.data.City
import com.example.dependent_spinner.data.State

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city:List<City>)

    @Query("SELECT city FROM city WHERE state =:id")
    fun getData(id: String): LiveData<List<String>>

    @Query("DELETE FROM city")
    suspend fun delete()
}