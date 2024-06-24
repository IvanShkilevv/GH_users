package com.example.ghusers.domain

import com.example.ghusers.Constants.PAGE_SIZE
import com.example.ghusers.data.model.User
import com.example.ghusers.data.source.UsersService
import javax.inject.Inject

class UsersInteractor @Inject constructor(
    private val usersService: UsersService
) {

    suspend fun loadUsersPage(pageSinceID: Int): List<User> {
        return usersService.getUsers(since = pageSinceID, perPage = PAGE_SIZE)
    }

//    suspend fun loadUsers(): List<User> {
//        return usersService.getUsers()
//    }

    suspend fun loadUserDetails(login: String): User {
        return usersService.getUserDetails(login)
    }

}