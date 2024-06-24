package com.example.ghusers.ui.users

import com.example.ghusers.domain.UsersInteractor
import com.example.ghusers.ui.base.BaseViewModel
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val usersInteractor: UsersInteractor
) : BaseViewModel() {

}