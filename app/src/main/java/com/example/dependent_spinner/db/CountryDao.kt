package com.example.dependent_spinner.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dependent_spinner.data.Country

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country:List<Country>)

    @Query("SELECT country FROM country")
    fun getData():List<String>

    @Query("DELETE FROM country")
    suspend fun delete()
}