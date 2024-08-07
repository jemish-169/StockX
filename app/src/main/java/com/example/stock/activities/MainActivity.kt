package com.example.stock.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.stock.core.theme.AppTheme
import com.example.stock.features.auth.presentation.MyApp
import com.example.stock.features.auth.presentation.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userViewModel.getUsers()

        setContent {
            AppTheme {
                MyApp(userViewModel)
            }
        }
    }
}
