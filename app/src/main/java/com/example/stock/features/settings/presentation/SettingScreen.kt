package com.example.stock.features.settings.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stock.core.routes.SignInScreen
import com.example.stock.features.auth.presentation.AuthViewModel
import com.example.stock.features.settings.presentation.viewmodel.SettingsViewModel

@Composable
fun SettingScreen(
    authViewModel: AuthViewModel,
    settingsViewModel: SettingsViewModel,
    navController: NavHostController
) {
    var signOutAlert by remember { mutableStateOf(false) }

    if (signOutAlert) {
        SignOutAlertBox(
            onDismiss = { signOutAlert = false },
            onSignOut = {
                signOutAlert = false
                authViewModel.signOut()
                authViewModel.clearUserState()
                navController.navigate(SignInScreen) {
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            })
    }

    ThemeSelection(settingsViewModel = settingsViewModel)

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
    )

    DynamicColorTheme(settingsViewModel = settingsViewModel)

    SignOutBoxItem(onClick = { signOutAlert = true })
}