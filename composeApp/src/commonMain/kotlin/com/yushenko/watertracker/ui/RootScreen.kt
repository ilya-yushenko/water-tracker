package com.yushenko.watertracker.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tide.common.AppTypography
import com.tide.common.BottomSheetType
import com.tide.common.appColorPalette
import com.tide.hydration.ui.HydrationScreen
import com.tide.hydration.ui.TrackHydrationScreen
import com.tide.history.ui.HistoryScreen
import com.tide.settings.ui.SettingsScreen
import com.tide.settings.ui.StatisticsScreen
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import watertracker.composeapp.generated.resources.Res
import watertracker.composeapp.generated.resources.ic_history
import watertracker.composeapp.generated.resources.ic_home
import watertracker.composeapp.generated.resources.ic_settings
import watertracker.composeapp.generated.resources.ic_statistics
import watertracker.composeapp.generated.resources.tab_history
import watertracker.composeapp.generated.resources.tab_home
import watertracker.composeapp.generated.resources.tab_settings
import watertracker.composeapp.generated.resources.tab_statistics

data class BottomNavigationItem(
    val title: String,
    val icon: Painter,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val route: String,
)

@Composable
fun RootScreen() {
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val bottomNavState = remember { mutableStateOf(true) }

    var currentBottomSheetContent by remember { mutableStateOf<BottomSheetType?>(null) }

    BottomSheetHost(
        sheetState = sheetState,
        currentBottomSheetContent = currentBottomSheetContent,
        sheetContent = { sheetData ->
            when (sheetData) {
                is BottomSheetType.TrackHydration -> {
                    TrackHydrationScreen(
                        onDismiss = { coroutineScope.launch { sheetState.hide() } }
                    )
                }
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            bottomBar = {
                if (bottomNavState.value) {
                    BottomNavigationBar(navController = navController)
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                NavigationHost(
                    navController = navController,
                    bottomNavState = bottomNavState,
                    onOpenBottomSheet = { sheetContent ->
                        currentBottomSheetContent = sheetContent
                        coroutineScope.launch { sheetState.show() }
                    }
                )
            }
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val colors = appColorPalette()

    val items = listOf(
        BottomNavigationItem(
            title = stringResource(Res.string.tab_home),
            icon = painterResource(Res.drawable.ic_home),
            hasNews = false,
            route = "home"
        ),
        BottomNavigationItem(
            title = stringResource(Res.string.tab_statistics),
            icon = painterResource(Res.drawable.ic_statistics),
            hasNews = false,
            route = "statistics"
        ),
        BottomNavigationItem(
            title = stringResource(Res.string.tab_history),
            icon = painterResource(Res.drawable.ic_history),
            hasNews = false,
            route = "history"
        ),
        BottomNavigationItem(
            title = stringResource(Res.string.tab_settings),
            icon = painterResource(Res.drawable.ic_settings),
            hasNews = false,
            route = "settings"
        )
    )

    BottomNavigation(backgroundColor = colors.bottomNav) {
        var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.route) {
                        launchSingleTop = true
                    }
                },

                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            } else if (item.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            painter = item.icon,
                            tint = if (index == selectedItemIndex) colors.blue else colors.gray,
                            contentDescription = item.title
                        )
                    }
                },
                label = {
                    Text(
                        fontFamily = AppTypography.medium(),
                        text = item.title,
                        color = if (index == selectedItemIndex) colors.blue else colors.gray,
                    )
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    bottomNavState: MutableState<Boolean>,
    onOpenBottomSheet: (BottomSheetType) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.Home.route
    ) {
        composable(ScreenRoutes.Home.route) {
            bottomNavState.value = true
//            HomeScreen(navController, bottomNavState)
            HydrationScreen(onOpenBottomSheet = onOpenBottomSheet)
        }
        composable(ScreenRoutes.Statistics.route) {
            bottomNavState.value = true
//            StatisticsScreen(navController)
            StatisticsScreen()
        }
        composable(ScreenRoutes.History.route) {
            bottomNavState.value = true
//            HistoryScreen(navController)
            HistoryScreen()
        }
        composable(ScreenRoutes.Settings.route) {
            bottomNavState.value = true
//            SettingsScreen(navController)
            SettingsScreen()
        }
    }

}

enum class ScreenRoutes(val route: String) {
    Home("home"),
    Statistics("statistics"),
    History("history"),
    Settings("settings")
}