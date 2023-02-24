package com.example.bnettestdudnikov.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DBDrug")
data class Drug(
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String,
    val description: String
)