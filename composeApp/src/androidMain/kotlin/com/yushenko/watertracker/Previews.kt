package com.yushenko.watertracker

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tide.settings.ui.HistoryScreen
import com.tide.hydration.ui.HomeScreen
import com.tide.hydration.ui.TrackHydrationScreen
import com.yushenko.watertracker.ui.RootScreen
import com.tide.settings.ui.SettingsScreen
import com.tide.settings.ui.StatisticsScreen

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun RootScreenPreview() {
    RootScreen()
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen({})
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun TrackHydrationScreen() {
    TrackHydrationScreen({})
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun StatisticsScreenPreview() {
    StatisticsScreen()
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun HistoryScreenPreview() {
    HistoryScreen()
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}