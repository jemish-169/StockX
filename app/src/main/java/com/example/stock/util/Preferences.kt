package com.example.stock.util

import android.content.Context
import android.content.SharedPreferences
import com.example.stock.features.auth.domain.User
import com.example.stock.util.Constants.Companion.ACCESS_TOKEN
import com.example.stock.util.Constants.Companion.REMEMBER_USER_EMAIL
import com.example.stock.util.Constants.Companion.REMEMBER_USER_PASSWORD

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
    }
}
