package com.example.stock.features.home.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Input
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MoveDown
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.StackedBarChart
import androidx.navigation.NavHostController
import com.example.stock.core.routes.AddProductScreen
import com.example.stock.core.routes.CreatePOScreen
import com.example.stock.core.routes.CreateSOScreen
import com.example.stock.core.routes.HistoryScreen
import com.example.stock.core.routes.MoveScreen
import com.example.stock.core.routes.StockScreen

fun getHomeMenuList(navController: NavHostController): List<HomeMenu> {
    return listOf(
        HomeMenu(name = "Add Product", Icons.Filled.Add, "add product")
        { navController.navigate(AddProductScreen) },

        HomeMenu(name = "Create SO", Icons.Default.Output, "Generate SO")
        { navController.navigate(CreateSOScreen) },

        HomeMenu(name = "Create PO", Icons.AutoMirrored.Filled.Input, "Generate PO")
        { navController.navigate(CreatePOScreen) },

        HomeMenu(name = "Move items", Icons.Default.MoveDown, "Move items")
        { navController.navigate(MoveScreen) },

        HomeMenu(name = "Stock", Icons.Default.StackedBarChart, "stock check")
        { navController.navigate(StockScreen) },

        HomeMenu(name = "History", Icons.Default.History, "records")
        { navController.navigate(HistoryScreen) },
    )
}