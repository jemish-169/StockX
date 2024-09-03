package com.example.stock.features.home.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stock.R
import com.example.stock.core.routes.SignInScreen
import com.example.stock.features.auth.presentation.AuthViewModel
import com.example.stock.features.home.domain.HomeMenu
import com.example.stock.features.home.domain.getHomeMenuList

@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    authViewModel: AuthViewModel,
    navController: NavHostController
) {
    val menuList = getHomeMenuList(navController)
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        CustomDialog(onDismissRequest = { showDialog = false }) {
            DialogContent(icon = R.drawable.warning_24,
                iconTint = MaterialTheme.colorScheme.onSurface,
                iconDesc = stringResource(R.string.warning),
                titleText = stringResource(R.string.are_you_sure),
                descText = stringResource(R.string.sign_out_text),
                positiveBtn = stringResource(R.string.sign_out),
                negativeBtn = stringResource(R.string.cancel),
                onNegativeClick = { showDialog = false },
                onPositiveClick = {
                    showDialog = false
                    authViewModel.signOut()
                    authViewModel.clearUserState()
                    navController.navigate(SignInScreen) {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                })
        }
    }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
    ) {

        // top header row
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            IconButton(onClick = {
                showDialog = true
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.Logout,
                    contentDescription = "Sign out button",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.FixedSize(124.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(menuList) { item ->
                ItemView(item)
            }
        }
    }
}

@Composable
fun ItemView(item: HomeMenu) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .border(
                BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                RoundedCornerShape(8.dp)
            )
            .clickable { item.onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = item.imageVector,
                contentDescription = item.imageDescription,
                modifier = Modifier.size(56.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.name,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}