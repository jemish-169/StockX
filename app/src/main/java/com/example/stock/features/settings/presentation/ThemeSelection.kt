package com.example.stock.features.settings.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stock.R
import com.example.stock.features.settings.domain.getIntFromTheme
import com.example.stock.features.settings.domain.getThemeFromInt
import com.example.stock.features.settings.presentation.viewmodel.SettingsViewModel
import com.example.stock.util.ThemeOption

@Composable
fun ThemeSelection(settingsViewModel: SettingsViewModel) {

    val items = listOf("Auto", "Light", "Dark")

    var selectedItem by remember { mutableIntStateOf(getIntFromTheme()) }

    Text(
        style = MaterialTheme.typography.titleMedium,
        text = stringResource(id = R.string.theme),
        fontWeight = FontWeight.Light,
        fontSize = 18.sp,
        modifier = Modifier.padding(8.dp)
    )

    SegmentedButton(items, selectedItem) {
        selectedItem = it
        settingsViewModel.setTheme(getThemeFromInt(it))
    }
}

@Composable
fun DynamicColorTheme(settingsViewModel: SettingsViewModel) {

    val items = listOf("Your system", "Pick your own")
    var selectedItem by remember { mutableIntStateOf(0) }

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
        selectedItem = it
        settingsViewModel.setTheme(if (it == 0) ThemeOption.SYSTEM else ThemeOption.CUSTOM)
    }

    if (selectedItem == 1) {
        ColorSeekBar(settingsViewModel)
    }
}

