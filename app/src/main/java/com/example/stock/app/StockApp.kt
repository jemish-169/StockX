package com.example.stock.app

import android.app.Application
import com.example.stock.util.Preferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StockApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Preferences.getInstance(this)
    }
}
