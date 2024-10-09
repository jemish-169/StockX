package com.example.stock.features.settings.presentation.themeUtils

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.collectForPress(
    interactionSource: InteractionSource,
    setOffset: (Offset) -> Unit
) {
    launch {
        interactionSource.interactions.collect { interaction ->
            (interaction as? PressInteraction.Press)
                ?.pressPosition
                ?.let(setOffset)
        }
    }
}

fun generatePastelColors(): List<Color> {
    val pastelColors = mutableListOf<Color>()
    for (hue in 0..300 step 30) {
        val hsvColor = android.graphics.Color.HSVToColor(floatArrayOf(hue.toFloat(), 0.55f, 1.0f))
        pastelColors.add(Color(hsvColor))
    }
    return pastelColors
}

fun generateFirstPastelColor(): Color {
    val hsvColor = android.graphics.Color.HSVToColor(floatArrayOf(0f, 0.55f, 1.0f))
    return Color(hsvColor)
}