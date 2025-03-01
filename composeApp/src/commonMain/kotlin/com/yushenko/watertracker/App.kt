package com.yushenko.watertracker

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tide.common.AppTheme
import com.yushenko.watertracker.ui.RootScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        Surface(
            modifier = Modifier.run {
                fillMaxSize()
                if (getPlatform() == Platform.Android) {
                    windowInsetsPadding(WindowInsets.navigationBars)
                } else {
                    padding(bottom = 0.dp)
                }
            },
        ) {
            RootScreen()
        }
    }
}