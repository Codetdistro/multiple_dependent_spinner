package com.example.dependent_spinner.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dependent_spinner.data.Country
import com.example.dependent_spinner.data.State

@Dao
interface StateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(state:List<State>)

    @Query("SELECT state FROM state WHERE country =:id")
    fun getData(id: String):LiveData<List<String>>

    @Query("DELETE FROM state")
    suspend fun delete()
}