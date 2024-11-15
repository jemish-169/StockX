package com.example.stock.core.routes

import kotlinx.serialization.Serializable

sealed class Routes() {
    @Serializable
    data object SignInScreen : Routes()

    @Serializable
    data object SignUpScreen : Routes()

    @Serializable
    data object HomeScreen : Routes()

    @Serializable
    data object AddProductScreen : Routes()

    @Serializable
    data object CreateSOScreen : Routes()

    @Serializable
    data object CreatePOScreen : Routes()

    @Serializable
    data object HistoryScreen : Routes()

    @Serializable
    data object MoveScreen : Routes()

    @Serializable
    data object StockScreen : Routes()

    @Serializable
    data object SettingScreen : Routes()
}