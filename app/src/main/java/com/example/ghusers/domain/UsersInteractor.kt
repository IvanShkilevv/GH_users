package com.example.ghusers.domain

import com.example.ghusers.data.source.UsersService
import javax.inject.Inject

class UsersInteractor @Inject constructor(
    private val usersService: UsersService
) {


}