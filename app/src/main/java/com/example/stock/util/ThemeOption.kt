package com.example.stock.util

enum class ThemeOption(val displayName: String) {
    DARK("Dark"),
    LIGHT("Light"),
    AUTO("Auto"),
    CUSTOM("Custom"),
    SYSTEM("System")
}

sealed class DynamicThemeOption(val color: Int) {
    class AUTO : DynamicThemeOption(1)
    class LIGHT : DynamicThemeOption(2)
    class DARK : DynamicThemeOption(3)
    class SYSTEM : DynamicThemeOption(4)
    class CUSTOM : DynamicThemeOption(5)
}