package com.example.dependent_spinner.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class viewmodelfactory(val repo:repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewmodel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return viewmodel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}