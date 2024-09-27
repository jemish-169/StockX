package com.example.stock.features.settings.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.stock.features.settings.presentation.themeUtils.collectForPress
import com.example.stock.features.settings.presentation.themeUtils.generatePastelColors
import com.example.stock.features.settings.presentation.viewmodel.SettingsViewModel
import com.example.stock.util.ThemeOption
import kotlinx.coroutines.launch


@Composable
fun ColorSeekBar(settingsViewModel: SettingsViewModel) {
    val pastelColors = generatePastelColors()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var hsv by remember {
            mutableStateOf(
                Triple(0f, 0.55f, 1.0f)
            )
        }

        HueBar(
            hsv = hsv,
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth()
                .height(48.dp),
            colorList = pastelColors,
            setColor = { hue ->
                hsv = Triple(hue, hsv.second, hsv.third)
                settingsViewModel.setCustomThemeColor(Color.hsv(hsv.first, hsv.second, hsv.third), ThemeOption.CUSTOM)
            })
    }
}

@Composable
fun HueBar(
    hsv: Triple<Float, Float, Float>,
    modifier: Modifier,
    barHeightDp: Dp = 20.dp,
    circleRadiusDp: Dp = 15.dp,
    circleBoarderWidthDp: Dp = 8.dp,
    borderColor: Color = Color.White,
    colorList: List<Color>,
    setColor: (Float) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val pressOffset = remember { mutableStateOf(Offset.Zero) }


    Canvas(
        modifier = modifier
            .emitDragGesture(interactionSource)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val barHeight = barHeightDp.toPx()
        val circleRadius = circleRadiusDp.toPx()
        val circleBoarderWidth = circleBoarderWidthDp.toPx()

        val circleShadowWidth = circleRadius + 0.3.dp.toPx() + circleBoarderWidth / 2
        val barTop = (canvasHeight - barHeight) / 2
        val barCornerRadius = barHeight / 2

        // Draw the hue gradient bar
        drawRoundRect(
            brush = Brush.horizontalGradient(
                colors = colorList,
                startX = 0f,
                endX = canvasWidth
            ),
            topLeft = Offset(0f, barTop),
            size = Size(canvasWidth, barHeight),
            cornerRadius = CornerRadius(barCornerRadius)
        )

        scope.collectForPress(interactionSource) { pressPosition ->
            val pressPos = pressPosition.x.coerceIn(0f..canvasWidth)
            pressOffset.value = Offset(pressPos, 0f)
            val selectedHue = pointToHue(pressPos, canvasWidth)
            setColor(selectedHue)
        }

        // Draw shadow for the circle indicator
        drawCircle(
            color = Color.Black.copy(alpha = 0.3f),
            radius = circleShadowWidth,
            center = Offset(
                x = pressOffset.value.x + 0.5.dp.toPx(),
                y = canvasHeight / 2 + 2.dp.toPx()
            )
        )

        // Draw the circle indicator
        drawCircle(
            color = borderColor,
            radius = circleRadius,
            center = Offset(pressOffset.value.x, canvasHeight / 2),
            style = Stroke(width = circleBoarderWidth)
        )

        // Draw the inner circle with the current HSV color
        drawCircle(
            color = Color.hsv(hsv.first, hsv.second, hsv.third),
            radius = circleRadius - circleBoarderWidth / 2,
            center = Offset(pressOffset.value.x, canvasHeight / 2)
        )
    }
}

fun Modifier.emitDragGesture(
    interactionSource: MutableInteractionSource
): Modifier = composed {
    val scope = rememberCoroutineScope()

    pointerInput(Unit) {
        detectDragGestures { input, _ ->
            scope.launch {
                interactionSource.emit(PressInteraction.Press(input.position))
            }
        }
    }.clickable(interactionSource, null) {
    }
}

fun pointToHue(pointX: Float, width: Float): Float {
    return (pointX.coerceIn(0f, width) / width) * 360f
}
