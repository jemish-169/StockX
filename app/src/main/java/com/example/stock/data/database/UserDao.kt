package com.example.stock.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.stock.features.auth.domain.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Insert
    suspend fun insertUser(user: User)
}
