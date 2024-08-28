package com.example.stock.features.home.domain

import androidx.compose.ui.graphics.vector.ImageVector

data class HomeMenu(
    val name: String,
    val imageVector: ImageVector,
    val imageDescription: String,
    val onClick: () -> Unit
)