package com.example.ghusers.ui.users

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.example.ghusers.data.model.User
import com.example.ghusers.domain.UsersInteractor
import com.example.ghusers.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val usersInteractor: UsersInteractor
) : BaseViewModel() {

    val uiState = mutableStateOf(UsersUiState.LOADING)
    val usersData = MutableStateFlow<List<User>>(emptyList())

}