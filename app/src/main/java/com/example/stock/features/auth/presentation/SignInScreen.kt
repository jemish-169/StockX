package com.example.stock.features.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stock.R
import com.example.stock.core.routes.HomeScreen
import com.example.stock.core.routes.SignUpScreen
import com.example.stock.features.auth.domain.User
import com.example.stock.features.auth.domain.AuthState
import com.example.stock.util.Preferences

@Composable
fun SignInScreen(
    innerPadding: PaddingValues,
    authViewModel: AuthViewModel,
    navController: NavHostController
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var rememberCheckBox by remember { mutableStateOf(true) }
    val userState by authViewModel.authState
    var currentUserState by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val user = Preferences.getRememberUser()
        email = TextFieldValue(user.email)
        password = TextFieldValue(user.password)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // app icon with welcome text
        Image(
            modifier = Modifier
                .height(200.dp)
                .width(200.dp),
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = "App icon"
        )

        Text(
            text = "Welcome Back!",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(32.dp))
        // Email outlined field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Password outlined field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            trailingIcon = {
                val icon = if (isPasswordVisible) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                }
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Password Visibility Toggle",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Remember Me Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = rememberCheckBox,
                    onCheckedChange = { rememberCheckBox = !rememberCheckBox }
                )
                Text(text = "Remember Me", color = MaterialTheme.colorScheme.onSurface)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sign In Button
        Button(
            onClick = {
                authViewModel.signInUser(
                    User(0, email.text, password.text),
                    rememberCheckBox
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Sign In", style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Don't have an account? Sign Up Text
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Don’t have an account?", color = MaterialTheme.colorScheme.onSurface)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Sign Up",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    authViewModel.clearUserState()
                    navController.navigate(SignUpScreen)
                }
            )
        }

        when (userState) {

            AuthState.Nothing -> {}

            is AuthState.Loading -> {
                LoadingComponent()
            }

            is AuthState.Success -> {
                val message = (userState as AuthState.Success).message
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
                val message = (userState as AuthState.Error).message
                currentUserState = message
            }

        }

        if (currentUserState.isNotEmpty()) {
            LoadingComponent(text = currentUserState)
        }
    }
}

@Composable
fun LoadingComponent(text: String = "") {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = if (text == "") "Loading..." else text)
    }
}
