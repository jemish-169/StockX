package com.example.stock.features.auth.domain

import com.example.stock.data.database.UserDao
import com.example.stock.data.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val apiService: ApiService
) {
    suspend fun getAllUsers(): List<User> = userDao.getAllUsers()

    suspend fun insertUser(user: User) = userDao.insertUser(user)

    suspend fun getSomeData(): Result<User> {
        return try {
            val response = apiService.getSomeData()
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun postSomeData(user: User): Result<User> {
        return try {
            val response = apiService.postSomeData(user)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
