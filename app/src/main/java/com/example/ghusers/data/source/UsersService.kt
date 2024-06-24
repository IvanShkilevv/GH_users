package com.example.ghusers.data.source

import com.example.ghusers.data.model.User
import retrofit2.http.GET

interface UsersService {

    @GET("users")
    suspend fun getUsers(): List<User>
}