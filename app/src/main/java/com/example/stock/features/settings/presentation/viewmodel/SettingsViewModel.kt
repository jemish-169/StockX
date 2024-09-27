package com.example.stock.features.settings.presentation.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock.util.Preferences
import com.example.stock.util.ThemeOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {

    private val _themeOption = MutableStateFlow(ThemeOption.AUTO)
    val themeOption: StateFlow<ThemeOption> = _themeOption

    init {
        viewModelScope.launch {
            _themeOption.value = Preferences.getTheme()
        }
    }

    fun setTheme(themeOption: ThemeOption) {
        viewModelScope.launch {
            Preferences.setTheme(themeOption)
            _themeOption.value = themeOption
        }
    }

    fun setCustomThemeColor(color: Color,themeOption: ThemeOption) {
        viewModelScope.launch {
            Preferences.setTheme(themeOption)
            Preferences.setThemeColor(color)
            _themeOption.value = ThemeOption.CUSTOM
        }
    }
}
