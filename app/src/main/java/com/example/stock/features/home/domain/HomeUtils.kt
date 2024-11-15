package com.example.stock.features.home.domain

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Input
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MoveDown
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.StackedBarChart
import androidx.navigation.NavHostController
import com.example.stock.R
import com.example.stock.core.routes.Routes.AddProductScreen
import com.example.stock.core.routes.Routes.CreatePOScreen
import com.example.stock.core.routes.Routes.CreateSOScreen
import com.example.stock.core.routes.Routes.HistoryScreen
import com.example.stock.core.routes.Routes.MoveScreen
import com.example.stock.core.routes.Routes.SettingScreen
import com.example.stock.core.routes.Routes.StockScreen

fun getHomeMenuList(context: Context, navController: NavHostController): List<HomeMenu> {
    return listOf(
        HomeMenu(
            name = context.getString(R.string.add_product),
            icon = Icons.Filled.Add,
            onClick = { navController.navigate(AddProductScreen) }
        ),
        HomeMenu(
            name = context.getString(R.string.create_so),
            icon = Icons.Default.Output,
            onClick = { navController.navigate(CreateSOScreen) }
        ),

        HomeMenu(
            name = context.getString(R.string.create_po),
            icon = Icons.AutoMirrored.Filled.Input,
            onClick = { navController.navigate(CreatePOScreen) }
        ),

        HomeMenu(
            name = context.getString(R.string.move_items),
            icon = Icons.Default.MoveDown,
            onClick = { navController.navigate(MoveScreen) }
        ),

        HomeMenu(
            name = context.getString(R.string.stock),
            icon = Icons.Default.StackedBarChart,
            onClick = { navController.navigate(StockScreen) }
        ),

        HomeMenu(
            name = context.getString(R.string.history),
            icon = Icons.Default.History,
            onClick = { navController.navigate(HistoryScreen) }
        ),

        HomeMenu(
            name = context.getString(R.string.settings),
            icon = Icons.Default.Settings,
            onClick = { navController.navigate(SettingScreen) },
        )
    )
}