package com.yushenko.watertracker

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yushenko.watertracker.theme.AppTheme
import com.yushenko.watertracker.ui.screens.root.RootScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            RootScreen()
        }
    }
}