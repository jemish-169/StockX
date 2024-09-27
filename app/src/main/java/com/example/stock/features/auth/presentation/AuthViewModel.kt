package com.example.stock.features.auth.presentation

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock.features.auth.domain.AuthErrorReason
import com.example.stock.features.auth.domain.AuthRepository
import com.example.stock.features.auth.domain.AuthState
import com.example.stock.features.auth.domain.User
import com.example.stock.util.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val _authState = mutableStateOf<Pair<AuthState, AuthErrorReason>>(
        Pair(
            AuthState.Nothing,
            AuthErrorReason.NONE
        )
    )
    val authState: State<Pair<AuthState, AuthErrorReason>> = _authState

    fun signInUser(user: User, isRemember: Boolean) {
        viewModelScope.launch {
            if (user.email.isEmpty()) _authState.value =
                Pair(AuthState.Error("Email is empty!"), AuthErrorReason.EMAIL)

            else if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) _authState.value =
                Pair(AuthState.Error("Email is Not valid!"), AuthErrorReason.EMAIL)

            else if (user.password.isEmpty()) _authState.value =
                Pair(AuthState.Error("Password is empty!"), AuthErrorReason.PASSWORD)

            else {
                _authState.value = Pair(AuthState.Loading, AuthErrorReason.NONE)
                _authState.value = Pair(repository.signInUser(user), AuthErrorReason.NONE)
                if (isRemember) {
                    Preferences.saveRememberUser(user)
                } else {
                    Preferences.clearRememberUser()
                }
            }
        }
    }

    fun signUpUser(user: User, isRemember: Boolean) {
        viewModelScope.launch {
            if (user.email.isEmpty()) _authState.value =
                Pair(AuthState.Error("Email is empty!"), AuthErrorReason.EMAIL)

            else if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) _authState.value =
                Pair(AuthState.Error("Email is Not valid!"), AuthErrorReason.EMAIL)

            else if (user.password.isEmpty()) _authState.value =
                Pair(AuthState.Error("Password is empty!"), AuthErrorReason.PASSWORD)

            else {
                _authState.value = Pair(AuthState.Loading, AuthErrorReason.NONE)
                _authState.value = Pair(repository.signUpUser(user), AuthErrorReason.NONE)
                if (isRemember) {
                    Preferences.saveRememberUser(user)
                } else {
                    Preferences.clearRememberUser()
                }
            }
        }
    }

    fun clearUserState() {
        _authState.value = Pair(AuthState.Nothing, AuthErrorReason.NONE)
    }

    fun signOut() {
        viewModelScope.launch {
            repository.signOut()
        }
    }
}
