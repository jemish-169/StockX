package com.example.stock.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stock.features.auth.domain.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
