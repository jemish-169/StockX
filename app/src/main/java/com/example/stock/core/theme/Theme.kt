package com.example.stock.core.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.stock.util.Preferences
import com.example.stock.util.ThemeOption
import com.materialkolor.DynamicMaterialTheme
import com.materialkolor.dynamicColorScheme
import com.materialkolor.rememberDynamicMaterialThemeState

private val DarkColorScheme = darkColorScheme(
    primary = LightTeal,
    onPrimary = Black,
    primaryContainer = DarkTeal,
    onPrimaryContainer = White,
    secondary = LightGrey,
    onSecondary = Black,
    secondaryContainer = DarkGrey,
    onSecondaryContainer = White,
    background = Black,
    onBackground = White,
    surface = Black,
    onSurface = White,
)

private val LightColorScheme = lightColorScheme(
    primary = DarkTeal,
    onPrimary = White,
    primaryContainer = LightTeal,
    onPrimaryContainer = Black,
    secondary = DarkGrey,
    onSecondary = White,
    secondaryContainer = LightGrey,
    onSecondaryContainer = Black,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
)

@Composable
fun AppTheme(
    themeOption: ThemeOption,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme =
        if (themeOption == ThemeOption.SYSTEM && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val selectedThemeColor = Preferences.getThemeColor()
            dynamicColorScheme(
                seedColor = selectedThemeColor,
                isDark = darkTheme || themeOption == ThemeOption.DARK,
                isAmoled = false
            )
        } else {
            when (themeOption) {
                ThemeOption.DARK -> DarkColorScheme

                ThemeOption.LIGHT -> LightColorScheme

                else -> if (darkTheme) DarkColorScheme else LightColorScheme
        }
    }


    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                if (themeOption == ThemeOption.AUTO || themeOption == ThemeOption.CUSTOM) {
                    if (colorScheme.primary.luminance() < 0.25f) false else true
                } else {
                    (darkTheme || themeOption == ThemeOption.DARK)
                }
        }
    }

    val state = rememberDynamicMaterialThemeState(
        seedColor = colorScheme.primary,
        isDark = darkTheme || themeOption == ThemeOption.DARK,
    )

    DynamicMaterialTheme(
        state = state,
        typography = Typography,
        content = content
    )
}