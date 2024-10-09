package com.example.stock.features.settings.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SegmentedButton(
    options: List<String>,
    selectedIndex: Int,
    onOptionSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .padding(2.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onSurface),
                shape = RoundedCornerShape(50)
            )
            .padding(3.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        options.forEachIndexed { index, option ->

            val backgroundColor by animateColorAsState(
                targetValue = if (index == selectedIndex) MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
                animationSpec = spring(stiffness = Spring.StiffnessHigh),
                label = ""
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(
                        color = backgroundColor
                    )
                    .weight(1f)
                    .clickable { onOptionSelected(index) }
                    .padding(horizontal = 8.dp)
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = option,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}