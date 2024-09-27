package com.example.stock.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.example.stock.base.ui.MainScreen
import com.example.stock.core.theme.AppTheme
import com.example.stock.features.add_product.presentation.AddProductViewModel
import com.example.stock.features.auth.presentation.AuthViewModel
import com.example.stock.features.settings.presentation.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()
            val authViewModel: AuthViewModel by viewModels()
            val addProductViewModel: AddProductViewModel by viewModels()
            val settingsViewModel: SettingsViewModel by viewModels()

            val themeOption = settingsViewModel.themeOption.collectAsState().value

            AppTheme(themeOption = themeOption) {
                MainScreen(
                    navController = navController,
                    addProductViewModel = addProductViewModel,
                    authViewModel = authViewModel,
                    settingsViewModel = settingsViewModel,
                    context = this
                )
            }
        }
    }
}
