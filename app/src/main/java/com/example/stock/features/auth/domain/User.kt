package com.example.stock.features.auth.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val email: String,
    val password: String
)
