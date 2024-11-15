package com.example.stock.features.settings.presentation

import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Stable
private data class SegmentDimensions(
    val itemWidth: Float = 0f,
    val itemHeight: Float = 0f
)

@Composable
fun SegmentedButton(
    options: List<String>,
    selectedIndex: Int,
    onOptionSelected: (Int) -> Unit
) {
    var dimensions by remember { mutableStateOf(SegmentDimensions()) }

    val transition = updateTransition(
        targetState = selectedIndex,
        label = "SegmentedButton"
    )

    val offsetX by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 200,
                easing = EaseInOutSine
            )
        },
        label = "offsetX",
        targetValueByState = { index -> dimensions.itemWidth * index }
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.surface)
            .border(
                border = BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                ),
                shape = RoundedCornerShape(50)
            )
            .padding(4.dp)
    ) {
        // Background selection indicator
        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .width(with(LocalDensity.current) { dimensions.itemWidth.toDp() })
                .height(with(LocalDensity.current) { dimensions.itemHeight.toDp() })
                .clip(RoundedCornerShape(50))
                .background(MaterialTheme.colorScheme.primaryContainer)
        )

        Row(
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    dimensions = SegmentDimensions(
                        itemWidth = coordinates.size.width / options.size.toFloat(),
                        itemHeight = coordinates.size.height.toFloat()
                    )
                },
            horizontalArrangement = Arrangement.Center
        ) {
            options.forEachIndexed { index, option ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(50))
                        .clickable { onOptionSelected(index) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = option,
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}