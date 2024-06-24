package com.example.ghusers.ui.users

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ghusers.data.model.User
import com.example.ghusers.domain.UsersInteractor
import com.example.ghusers.ui.base.BaseViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val usersInteractor: UsersInteractor
) : BaseViewModel() {

    val uiState = mutableStateOf(UsersUiState.LOADING)
    val usersData = MutableStateFlow<List<User>>(emptyList())

    fun loadUsers() {
        val job = viewModelScope.async {
            usersInteractor.loadUsers()
        }

        viewModelScope.launch {
            try {
                val items = job.await()
                usersData.value = items
                uiState.value = UsersUiState.LIST
            } catch (error: Throwable) {
                if (error is CancellationException) throw error else uiState.value = UsersUiState.ERROR
            }
        }
    }

}