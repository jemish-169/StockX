package com.example.stock.features.settings.presentation

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.stock.R

@Composable
fun SignOutAlertBox(onSignOut: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = stringResource(id = R.string.sign_out)) },
        text = { Text(text = stringResource(id = R.string.are_you_sure_sign_out)) },
        confirmButton = {
            TextButton(onClick = onSignOut) {
                Text(stringResource(id = R.string.yes))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.no))
            }
        }
    )
}