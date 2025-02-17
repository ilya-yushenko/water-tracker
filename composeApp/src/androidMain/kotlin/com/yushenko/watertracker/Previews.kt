package com.yushenko.watertracker

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yushenko.watertracker.ui.screens.home.HomeScreen
import com.yushenko.watertracker.ui.screens.settings.SettingsScreen
import com.yushenko.watertracker.ui.screens.statistics.StatisticsScreen

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
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
fun SettingsScreenPreview() {
    SettingsScreen()
}


//@SuppressLint("UnrememberedMutableState")
//@Preview
//@Composable
//fun StatisticsScreenPreview() {
//    val navController = rememberNavController()
//    StatisticsScreen(
//        navController = navController,
//        bottomNavState = mutableStateOf(false)
//    )
//}