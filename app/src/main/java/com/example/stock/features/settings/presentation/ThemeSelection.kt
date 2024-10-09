package com.example.stock.features.settings.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stock.R
import com.example.stock.features.settings.presentation.viewmodel.SettingsViewModel

@Composable
fun ThemeSelection(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {

    val items = listOf("Auto", "Light", "Dark")

    Text(
        style = MaterialTheme.typography.titleMedium,
        text = stringResource(id = R.string.theme),
        fontWeight = FontWeight.Light,
        fontSize = 18.sp,
        modifier = Modifier.padding(8.dp)
    )

    SegmentedButton(items, selectedItem) {
        onItemSelected(it)
    }
}

@Composable
fun DynamicColorTheme(
    settingsViewModel: SettingsViewModel,
    pastelColors: List<Color>,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    onColorSelected: (Color) -> Unit
) {

    val items = mutableListOf("Pick your own")
    if (settingsViewModel.isYourSystemEnabled()) {
        items.add(0, "Your system")
    }

    Text(
        style = MaterialTheme.typography.titleMedium,
        text = stringResource(id = R.string.dynamic_color),
        fontWeight = FontWeight.Light,
        fontSize = 18.sp,
        modifier = Modifier
            .padding(top = 8.dp)
            .padding(horizontal = 8.dp)
    )
    Text(
        style = MaterialTheme.typography.bodySmall,
        text = stringResource(id = R.string.choose_color),
        modifier = Modifier
            .padding(bottom = 8.dp)
            .padding(horizontal = 8.dp)
    )

    SegmentedButton(items, selectedItem) {
        onItemSelected(it)
    }

    AnimatedVisibility(
        visible = selectedItem == 1,
        enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
        exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Top)

    ) {
        ColorSeekBar(
            pastelColors = pastelColors,
            initialColor = settingsViewModel.getThemeColor(),
            onColorSelected = { color ->
                onColorSelected(color)
            })
    }
}

