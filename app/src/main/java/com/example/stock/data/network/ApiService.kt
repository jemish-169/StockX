package com.example.stock.data.network

import com.example.stock.features.auth.domain.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("/user")
    suspend fun getSomeData(): Response<User>

    @POST("/user")
    suspend fun postSomeData(@Body user: User): Response<User>
}
