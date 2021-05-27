package com.example.dependent_spinner.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "state")
data class State (@PrimaryKey val id:Int,val country:String,val state:String)