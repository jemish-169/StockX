package com.example.stock.base.ui

import android.content.Context
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stock.R
import com.example.stock.base.animation.AnimateScreen
import com.example.stock.core.routes.Routes.AddProductScreen
import com.example.stock.core.routes.Routes.CreatePOScreen
import com.example.stock.core.routes.Routes.CreateSOScreen
import com.example.stock.core.routes.Routes.HistoryScreen
import com.example.stock.core.routes.Routes.HomeScreen
import com.example.stock.core.routes.Routes.MoveScreen
import com.example.stock.core.routes.Routes.SettingScreen
import com.example.stock.core.routes.Routes.SignInScreen
import com.example.stock.core.routes.Routes.SignUpScreen
import com.example.stock.core.routes.Routes.StockScreen
import com.example.stock.features.add_product.presentation.AddProductScreen
import com.example.stock.features.add_product.presentation.AddProductViewModel
import com.example.stock.features.auth.presentation.AuthViewModel
import com.example.stock.features.auth.presentation.SignInScreen
import com.example.stock.features.auth.presentation.SignUpScreen
import com.example.stock.features.create_po.presentation.CreatePOScreen
import com.example.stock.features.create_so.presentation.CreateSOScreen
import com.example.stock.features.history.presentation.HistoryScreen
import com.example.stock.features.home.presentation.HomeScreen
import com.example.stock.features.move_items.presentation.MoveScreen
import com.example.stock.features.settings.presentation.SettingScreen
import com.example.stock.features.settings.presentation.viewmodel.SettingsViewModel
import com.example.stock.features.stock_check.presentation.StockScreen
import com.example.stock.util.Preferences

@Composable
fun MainScreen(
    navController: NavHostController,
    addProductViewModel: AddProductViewModel,
    authViewModel: AuthViewModel,
    context: Context,
    settingsViewModel: SettingsViewModel
) {
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = getStartDestination()
        ) {
            composable<SignInScreen>(
                popEnterTransition = AnimateScreen.popEnterTransition(),
                enterTransition = AnimateScreen.enterTransition(),
                popExitTransition = AnimateScreen.popExitTransition(),
                exitTransition = AnimateScreen.exitTransition()
            ) {
                SignInScreen(
                    innerPadding = innerPadding,
                    authViewModel = authViewModel,
                    navController = navController
                )
            }
            composable<SignUpScreen>(
                popEnterTransition = AnimateScreen.popEnterTransition(),
                enterTransition = AnimateScreen.enterTransition(),
                popExitTransition = AnimateScreen.popExitTransition(),
                exitTransition = AnimateScreen.exitTransition()
            ) {
                SignUpScreen(
                    innerPadding = innerPadding,
                    authViewModel = authViewModel,
                    navController = navController
                )
            }
            composable<HomeScreen>(
                popEnterTransition = AnimateScreen.popEnterTransition(),
                enterTransition = AnimateScreen.enterTransition(),
                popExitTransition = AnimateScreen.popExitTransition(),
                exitTransition = AnimateScreen.exitTransition()
            ) {
                BaseLayout(
                    title = stringResource(id = R.string.app_name),
                    innerPadding = innerPadding,
                    navController = navController,
                    isBackEnabled = false
                ) {
                    HomeScreen(
                        navController = navController,
                        context = context
                    )
                }
            }
            composable<AddProductScreen>(
                popEnterTransition = AnimateScreen.popEnterTransition(),
                enterTransition = AnimateScreen.enterTransition(),
                popExitTransition = AnimateScreen.popExitTransition(),
                exitTransition = AnimateScreen.exitTransition()
            ) {
                BaseLayout(
                    title = stringResource(id = R.string.add_product),
                    innerPadding = innerPadding,
                    navController = navController,
                    content = {
                        AddProductScreen(viewModel = addProductViewModel)
                    },
                )
            }
            composable<CreateSOScreen>(
                popEnterTransition = AnimateScreen.popEnterTransition(),
                enterTransition = AnimateScreen.enterTransition(),
                popExitTransition = AnimateScreen.popExitTransition(),
                exitTransition = AnimateScreen.exitTransition()
            ) {
                CreateSOScreen()
            }
            composable<CreatePOScreen>(
                popEnterTransition = AnimateScreen.popEnterTransition(),
                enterTransition = AnimateScreen.enterTransition(),
                popExitTransition = AnimateScreen.popExitTransition(),
                exitTransition = AnimateScreen.exitTransition()
            ) {
                CreatePOScreen()
            }
            composable<HistoryScreen>(
                popEnterTransition = AnimateScreen.popEnterTransition(),
                enterTransition = AnimateScreen.enterTransition(),
                popExitTransition = AnimateScreen.popExitTransition(),
                exitTransition = AnimateScreen.exitTransition()
            ) {
                HistoryScreen()
            }
            composable<MoveScreen>(
                popEnterTransition = AnimateScreen.popEnterTransition(),
                enterTransition = AnimateScreen.enterTransition(),
                popExitTransition = AnimateScreen.popExitTransition(),
                exitTransition = AnimateScreen.exitTransition()
            ) {
                MoveScreen()
            }
            composable<StockScreen>(
                popEnterTransition = AnimateScreen.popEnterTransition(),
                enterTransition = AnimateScreen.enterTransition(),
                popExitTransition = AnimateScreen.popExitTransition(),
                exitTransition = AnimateScreen.exitTransition()
            ) {
                StockScreen()
            }
            composable<SettingScreen>(
                popEnterTransition = AnimateScreen.popEnterTransition(),
                enterTransition = AnimateScreen.enterTransition(),
                popExitTransition = AnimateScreen.popExitTransition(),
                exitTransition = AnimateScreen.exitTransition()
            ) {
                BaseLayout(
                    title = stringResource(id = R.string.settings),
                    innerPadding = innerPadding,
                    navController = navController,
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    content = {
                        SettingScreen(
                            authViewModel = authViewModel,
                            settingsViewModel = settingsViewModel,
                            navController = navController
                        )
                    },
                )
            }
        }
    }
}

private fun getStartDestination(): Any {
    return if (Preferences.getAccessToken().isNullOrEmpty()) SignInScreen
    else HomeScreen
}