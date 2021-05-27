package com.example.dependent_spinner.model

import androidx.lifecycle.LiveData
import com.example.dependent_spinner.db.CountryDatabase

class repository(val db:CountryDatabase) {

    fun getCountry():List<String>{
        return db.countryDao().getData()
    }

    fun getState(country:String):LiveData<List<String>>{
        return db.stateDao().getData(country)
    }

    fun getCity(state:String):LiveData<List<String>>{
        return db.cityDao().getData(state)
    }
}