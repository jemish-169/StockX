package com.example.stock.features.auth.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock.features.auth.domain.User
import com.example.stock.features.auth.domain.AuthRepository
import com.example.stock.features.auth.domain.AuthState
import com.example.stock.util.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val _authState = mutableStateOf<AuthState>(AuthState.Nothing)
    val authState: State<AuthState> = _authState

    fun signInUser(user: User, isRemember: Boolean) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            _authState.value = repository.signInUser(user)
            if (isRemember) {
                Preferences.saveRememberUser(user)
            } else {
                Preferences.clearRememberUser()
            }
        }
    }

    fun signUpUser(user: User, isRemember: Boolean) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            _authState.value = repository.signUpUser(user)
            if (isRemember) {
                Preferences.saveRememberUser(user)
            } else {
                Preferences.clearRememberUser()
            }
        }
    }

    fun clearUserState() {
        _authState.value = AuthState.Nothing
    }

    fun signOut() {
        viewModelScope.launch {
            repository.signOut()
        }
    }
}
