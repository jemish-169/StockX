package com.example.stock.features.settings.domain

import com.example.stock.util.Preferences
import com.example.stock.util.ThemeOption

fun getThemeFromInt(theme: Int): ThemeOption {
    return when (theme) {
        1 -> ThemeOption.LIGHT
        2 -> ThemeOption.DARK
        else -> ThemeOption.AUTO
    }
}

fun getIntFromTheme(): Int {
    val theme = Preferences.getTheme()
    return when (theme) {
        ThemeOption.LIGHT -> 1
        ThemeOption.DARK -> 2
        else -> 0
    }
}