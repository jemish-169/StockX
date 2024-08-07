package com.example.stock.features.auth.domain

import com.example.stock.data.database.UserDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userDao: UserDao) {
    suspend fun getAllUsers(): List<User> = userDao.getAllUsers()

    suspend fun insertUser(user: User) = userDao.insertUser(user)
}
