package com.example.stock.util

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.stock.features.auth.domain.User
import com.example.stock.util.Constants.Companion.ACCESS_TOKEN
import com.example.stock.util.Constants.Companion.REMEMBER_USER_EMAIL
import com.example.stock.util.Constants.Companion.REMEMBER_USER_PASSWORD
import com.example.stock.util.Constants.Companion.SAVED_THEME_COLOR
import com.example.stock.util.Constants.Companion.THEME_NAME

class Preferences {
    companion object {

        private lateinit var appPref: SharedPreferences

        fun getInstance(context: Context): SharedPreferences {
            if (!Companion::appPref.isInitialized) appPref =
                context.getSharedPreferences(Constants.DATA, Context.MODE_PRIVATE)
            return appPref
        }

        fun setAccessToken(accessToken: String) {
            appPref.edit().putString(ACCESS_TOKEN, accessToken).apply()
        }

        fun removeAccessToken() {
            appPref.edit().remove(ACCESS_TOKEN).apply()
        }

        fun getAccessToken(): String? {
            return appPref.getString(ACCESS_TOKEN, "")
        }

        fun saveRememberUser(user: User) {
            appPref.edit().putString(REMEMBER_USER_EMAIL, user.email).apply()
            appPref.edit().putString(REMEMBER_USER_PASSWORD, user.password).apply()
        }

        fun clearRememberUser() {
            appPref.edit().remove(REMEMBER_USER_EMAIL).apply()
            appPref.edit().remove(REMEMBER_USER_PASSWORD).apply()
        }

        fun getRememberUser(): User {
            return User(
                uid = 0, email = appPref.getString(REMEMBER_USER_EMAIL, "").toString(),
                password = appPref.getString(REMEMBER_USER_PASSWORD, "").toString()
            )
        }

        fun getTheme(): ThemeOption {
            val theme = appPref.getString(THEME_NAME, ThemeOption.AUTO.name)
            return when (theme) {
                ThemeOption.LIGHT.name -> ThemeOption.LIGHT
                ThemeOption.DARK.name -> ThemeOption.DARK
                else -> ThemeOption.AUTO
            }
        }

        fun setTheme(themeOption: ThemeOption) {
            appPref.edit().putString(THEME_NAME, themeOption.name).apply()
        }

        fun setThemeColor(color: Color) {
            appPref.edit().putInt(SAVED_THEME_COLOR, color.toArgb()).apply()
        }

        fun getThemeColor(): Color {
            return Color(appPref.getInt(SAVED_THEME_COLOR, Color.Blue.copy(alpha = 0.5f).toArgb()))
        }
    }
}
