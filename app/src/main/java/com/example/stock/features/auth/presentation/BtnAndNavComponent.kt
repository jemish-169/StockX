package com.example.stock.features.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stock.features.auth.domain.AuthErrorReason
import com.example.stock.features.auth.domain.AuthState

@Composable
fun AuthButton(authState: Pair<AuthState, AuthErrorReason>, btnText: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        elevation = ButtonDefaults.elevatedButtonElevation(2.dp),
        enabled = authState.first !is AuthState.Loading,
        colors = ButtonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        if (authState.first is AuthState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(30.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        } else {
            Text(text = btnText, style = MaterialTheme.typography.bodyLarge)
        }
    }

    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun NavigationText(mainText: String, navText: String, modifier: Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = mainText, color = MaterialTheme.colorScheme.onSurface)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = navText,
            color = MaterialTheme.colorScheme.primary,
            modifier = modifier
        )
    }
}