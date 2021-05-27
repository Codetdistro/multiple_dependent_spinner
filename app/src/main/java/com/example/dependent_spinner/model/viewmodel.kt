package com.example.dependent_spinner.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class viewmodel(val repo: repository):ViewModel() {

    fun getCountry():List<String>{
        return repo.getCountry()
    }

    fun getState(country:String):LiveData<List<String>>{
        return repo.getState(country)
    }

    fun getCity(state: String):LiveData<List<String>>{
        return repo.getCity(state)
    }
}