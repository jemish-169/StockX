package com.example.stock.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stock.core.routes.AddProductScreen
import com.example.stock.core.routes.CreatePOScreen
import com.example.stock.core.routes.CreateSOScreen
import com.example.stock.core.routes.HistoryScreen
import com.example.stock.core.routes.HomeScreen
import com.example.stock.core.routes.MoveScreen
import com.example.stock.core.routes.SignInScreen
import com.example.stock.core.routes.SignUpScreen
import com.example.stock.core.routes.StockScreen
import com.example.stock.features.history.presentation.HistoryScreen
import com.example.stock.features.stock_check.presentation.StockScreen
import com.example.stock.features.move_items.presentation.MoveScreen
import com.example.stock.core.theme.AppTheme
import com.example.stock.features.add_product.presentation.AddProductScreen
import com.example.stock.features.auth.presentation.AuthViewModel
import com.example.stock.features.auth.presentation.SignInScreen
import com.example.stock.features.auth.presentation.SignUpScreen
import com.example.stock.features.create_po.presentation.CreatePOScreen
import com.example.stock.features.create_so.presentation.CreateSOScreen
import com.example.stock.features.home.presentation.HomeScreen
import com.example.stock.util.Preferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                MainScreen()
            }
        }
    }

    @Composable
    private fun MainScreen() {
        val navController = rememberNavController()
        val authViewModel: AuthViewModel by viewModels()

        Scaffold { innerPadding ->
            NavHost(navController = navController, startDestination = getStartDestination()) {
                composable<SignInScreen> {
                    SignInScreen(innerPadding, authViewModel, navController)
                }
                composable<SignUpScreen> {
                    SignUpScreen(innerPadding, authViewModel, navController)
                }
                composable<HomeScreen> {
                    HomeScreen(innerPadding, authViewModel, navController)
                }
                composable<AddProductScreen> {
                    AddProductScreen()
                }
                composable<CreateSOScreen> {
                    CreateSOScreen()
                }
                composable<CreatePOScreen> {
                    CreatePOScreen()
                }
                composable<HistoryScreen> {
                    HistoryScreen()
                }
                composable<MoveScreen> {
                    MoveScreen()
                }
                composable<StockScreen> {
                    StockScreen()
                }
            }
        }
    }

    private fun getStartDestination(): Any {
        return if (Preferences.getAccessToken().isNullOrEmpty()) SignInScreen
        else HomeScreen
    }
}
