package com.example.stock.features.settings.presentation.viewmodel

import android.os.Build
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock.features.settings.presentation.themeUtils.generatePastelColors
import com.example.stock.util.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {

    var getPastelColors: List<Color> = emptyList()

    private val _themeMode = MutableStateFlow(0)
    val themeMode: StateFlow<Int> = _themeMode

    private val _dynamicThemeMode = MutableStateFlow(0)
    val dynamicThemeMode: StateFlow<Int> = _dynamicThemeMode

    private val _themeColor = MutableStateFlow(Preferences.getThemeColor())
    val themeColor: StateFlow<Color> = _themeColor

    init {
        viewModelScope.launch {
            getPastelColors = generatePastelColors()
            _themeMode.value = Preferences.getSelectedThemeMode()
            _dynamicThemeMode.value = Preferences.getDynamicThemeMode()
            _themeColor.value = Preferences.getThemeColor()
        }
    }

    fun setTheme(
        themeColor: Color = Preferences.getThemeColor(),
        themeMode: Int,
        dynamicThemeMode: Int
    ) {
        viewModelScope.launch {
            Preferences.setSelectedThemeMode(themeMode)
            Preferences.setDynamicThemeMode(dynamicThemeMode)
            Preferences.setThemeColor(themeColor)
            _themeMode.value = themeMode
            _dynamicThemeMode.value = dynamicThemeMode
            _themeColor.value = themeColor
        }
    }

    fun isYourSystemEnabled(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    }

    fun getSelectedThemeMode(): Int {
        return Preferences.getSelectedThemeMode()
    }

    fun getDynamicThemeMode(): Int {
        return Preferences.getDynamicThemeMode()
    }

    fun clearAll() {
        Preferences.clearAllPref()
    }

    fun getThemeColor(): Color {
        return Preferences.getThemeColor()
    }
}
