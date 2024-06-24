package com.example.ghusers.ui.user_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ghusers.data.model.User
import com.example.ghusers.domain.UsersInteractor
import com.example.ghusers.ui.base.BaseViewModel
import com.example.ghusers.ui.users.UsersUiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(
    private val usersInteractor: UsersInteractor
) : BaseViewModel() {

    var userLogin: String = ""
    val uiState = mutableStateOf(UserDetailsUiState.LOADING)
    val userDetailsData = MutableLiveData<User>()

    fun loadUserDetails() {
        val deferred = viewModelScope.async {
//            delay added for a better testing PullToRefresh and ProgressIndicator
            delay(1500)
            usersInteractor.loadUserDetails(userLogin)
        }

        viewModelScope.launch {
            try {
                val result = deferred.await()
                userDetailsData.value = result
                uiState.value = UserDetailsUiState.DATA
            } catch (error: Throwable) {
                if (error is CancellationException) throw error else uiState.value = UserDetailsUiState.ERROR
            }
        }
    }

}