package com.example.stock.features.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.stock.R
import com.example.stock.features.auth.domain.AuthErrorReason
import com.example.stock.features.auth.domain.AuthState

@Composable
fun EmailTextField(
    email: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    authState: Pair<AuthState, AuthErrorReason>
) {
    OutlinedTextField(
        value = email,
        onValueChange = { onValueChange(it) },
        label = { Text(text = stringResource(id = R.string.email)) },
        singleLine = true,
        isError = authState.second == AuthErrorReason.EMAIL,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = stringResource(id = R.string.email_icon),
                tint = MaterialTheme.colorScheme.primary
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun PasswordTextField(
    password: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    authState: Pair<AuthState, AuthErrorReason>,
    isPasswordVisible: Boolean,
    onVisibilityToggle: () -> Unit
) {
    OutlinedTextField(
        value = password,
        onValueChange = { onValueChange(it) },
        label = { Text(text = stringResource(id = R.string.password)) },
        singleLine = true,
        isError = authState.second == AuthErrorReason.PASSWORD,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = stringResource(id = R.string.password_icon),
                tint = MaterialTheme.colorScheme.primary
            )
        },
        trailingIcon = {
            val icon = if (isPasswordVisible) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { onVisibilityToggle() }) {
                Icon(
                    imageVector = icon,
                    contentDescription = stringResource(id = R.string.password_visibility_toggle),
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
}

@Composable
fun RememberUserCB(rememberCheckBox: Boolean, onCBToggle: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = rememberCheckBox,
                onCheckedChange = { onCBToggle() }
            )
            Text(text = stringResource(id = R.string.remember_me), color = MaterialTheme.colorScheme.onSurface)
        }
    }

    Spacer(modifier = Modifier.height(24.dp))
}