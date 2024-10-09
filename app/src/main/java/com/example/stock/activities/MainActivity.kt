package com.example.stock.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
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

            val themeColor = settingsViewModel.themeColor.collectAsState().value
            val themeMode = settingsViewModel.themeMode.collectAsState().value
            val dynamicThemeMode = settingsViewModel.dynamicThemeMode.collectAsState().value


            val isDarkTheme =
                when (themeMode) {
                    0 -> isSystemInDarkTheme()
                    1 -> false
                    else -> true
                }

            val color =
                if (isDarkTheme && dynamicThemeMode == 0 && settingsViewModel.isYourSystemEnabled())
                    dynamicDarkColorScheme(this).primary
                else if (!isDarkTheme && dynamicThemeMode == 0 && settingsViewModel.isYourSystemEnabled())
                    dynamicLightColorScheme(this).primary
                else themeColor

            AppTheme(themeColor = color, darkTheme = isDarkTheme) {
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
