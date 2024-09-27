package com.example.stock.features.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stock.R
import com.example.stock.core.routes.HomeScreen
import com.example.stock.features.auth.domain.AuthState
import com.example.stock.features.auth.domain.User

@Composable
fun SignUpScreen(
    innerPadding: PaddingValues,
    authViewModel: AuthViewModel,
    navController: NavHostController
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var rememberCheckBox by remember { mutableStateOf(true) }
    val authState by authViewModel.authState
    var currentUserState by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // app icon with welcome text
        HeaderAndGreeting(
            greetingText = stringResource(id = R.string.welcome)
        )

        // Email outlined field
        EmailTextField(
            email = email,
            onValueChange = { email = it },
            authState = authState
        )

        // Password outlined field
        PasswordTextField(
            password = password,
            onValueChange = { password = it },
            authState = authState,
            isPasswordVisible = isPasswordVisible,
            onVisibilityToggle = { isPasswordVisible = !isPasswordVisible })

        // Remember Me Row
        RememberUserCB(
            rememberCheckBox = rememberCheckBox,
            onCBToggle = { rememberCheckBox = !rememberCheckBox })

        // Sign Up Button
        AuthButton(
            authState = authState,
            btnText = stringResource(id = R.string.sign_up),
            onClick = {
                if (authState.first !is AuthState.Loading)
                    authViewModel.signUpUser(
                        User(0, email.text, password.text),
                        rememberCheckBox
                    )
            })

        // Already have an account? Sign In Text
        NavigationText(
            mainText = stringResource(id = R.string.already_have_an_account),
            navText = stringResource(id = R.string.sign_in),
            modifier = Modifier.clickable {
                authViewModel.clearUserState()
                navController.navigateUp()
            })

        when (authState.first) {

            AuthState.Nothing -> {}

            is AuthState.Loading -> {
                DisplayAuthStatus(displayText = "")
            }

            is AuthState.Success -> {
                val message = (authState.first as AuthState.Success).message
                currentUserState = message
                LaunchedEffect(true) {
                    authViewModel.clearUserState()
                    navController.navigate(HomeScreen) {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                }
            }

            is AuthState.Error -> {
                val message = (authState.first as AuthState.Error).message
                currentUserState = message
            }
        }

        if (currentUserState.isNotEmpty()) {
            DisplayAuthStatus(displayText = currentUserState)
        }
    }
}