package com.example.ghusers.ui.users

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.ghusers.data.model.User
import com.example.ghusers.domain.UsersInteractor
import com.example.ghusers.ui.base.BaseViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val usersInteractor: UsersInteractor
) : BaseViewModel() {

    val uiState = mutableStateOf(UsersUiState.LOADING)
    val usersData = MutableStateFlow<List<User>>(emptyList())

    fun loadUsers(): Deferred<List<User>> {
        val deferred = viewModelScope.async {
//            delay added for a better testing PullToRefresh and ProgressIndicator
            delay(1500)
            usersInteractor.loadUsers()
        }

        viewModelScope.launch {
            try {
                val items = deferred.await()
                usersData.value = items
                uiState.value = UsersUiState.DATA
            } catch (error: Throwable) {
                if (error is CancellationException) throw error else uiState.value = UsersUiState.ERROR
            }
        }

        return deferred
    }

}