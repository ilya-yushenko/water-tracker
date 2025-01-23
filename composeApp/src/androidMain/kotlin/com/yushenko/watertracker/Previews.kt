package com.yushenko.watertracker

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yushenko.watertracker.ui.screens.home.HomeScreen
import com.yushenko.watertracker.ui.screens.settings.SettingsScreen

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
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