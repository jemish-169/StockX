package com.example.stock.features.auth.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock.features.auth.domain.User
import com.example.stock.features.auth.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    val users = mutableStateListOf<User>()

    private val _data = MutableLiveData<User>()
    val data: LiveData<User> get() = _data

    fun getUsers() {
        viewModelScope.launch {
            users.clear()
            users.addAll(repository.getAllUsers())
        }
    }

    fun addUser(firstName: String, lastName: String) {
        viewModelScope.launch {
            val user = User(firstName = firstName, lastName = lastName)
            repository.insertUser(user)
            getUsers()
        }
    }

    fun fetchSomeData() {
        viewModelScope.launch {
            val result = repository.getSomeData()
            if (result.isSuccess) {
                result.getOrNull()?.let { _data.value = it }
            } else {
                // Handle error
            }
        }
    }
}
