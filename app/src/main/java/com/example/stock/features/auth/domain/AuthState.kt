package com.example.stock.features.auth.domain

sealed class AuthState {
    data object Nothing : AuthState()
    data object Loading : AuthState()
    data class Success(val message: String) : AuthState()
    data class Error(val message: String) : AuthState()
}