package com.example.ghusers.data.source

import com.example.ghusers.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{login}")
    suspend fun getUserDetails(@Path("login") login: String): User

}