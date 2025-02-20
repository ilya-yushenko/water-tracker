package com.yushenko.watertracker.ui.screens.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yushenko.watertracker.theme.ColorBlue
import com.yushenko.watertracker.theme.ColorGray
import com.yushenko.watertracker.theme.ColorWhite
import com.yushenko.watertracker.ui.screens.history.HistoryScreen
import com.yushenko.watertracker.ui.screens.home.HomeScreen
import com.yushenko.watertracker.ui.screens.home.TrackHydrationScreen
import com.yushenko.watertracker.ui.screens.settings.SettingsScreen
import com.yushenko.watertracker.ui.screens.statistics.StatisticsScreen
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import watertracker.composeapp.generated.resources.Inter_Medium
import watertracker.composeapp.generated.resources.Res
import watertracker.composeapp.generated.resources.ic_history
import watertracker.composeapp.generated.resources.ic_home
import watertracker.composeapp.generated.resources.ic_settings
import watertracker.composeapp.generated.resources.ic_statistics
import watertracker.composeapp.generated.resources.tab_history
import watertracker.composeapp.generated.resources.tab_home
import watertracker.composeapp.generated.resources.tab_settings
import watertracker.composeapp.generated.resources.tab_statistics

sealed class BottomSheetContent {
    data object TrackHydration : BottomSheetContent()
}

data class BottomNavigationItem(
    val title: String,
    val icon: Painter,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val route: String,
)

@Composable
fun RootScreen() {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { newState ->
            newState != ModalBottomSheetValue.Expanded
        }
    )
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val bottomNavState = remember { mutableStateOf(true) }

    var currentBottomSheetContent by remember { mutableStateOf<BottomSheetContent?>(null) }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetElevation = 0.dp,
        scrimColor = Color.Black.copy(alpha = 0.32f),
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            when (currentBottomSheetContent) {
                is BottomSheetContent.TrackHydration -> TrackHydrationScreen(
                    onDismiss = {
                        coroutineScope.launch { sheetState.hide() }
                    }
                )

                else -> Unit
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
                modifier = Modifier
                    .padding(innerPadding)
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

    BottomNavigation(
        backgroundColor = ColorWhite
    ) {
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
                            tint = if (index == selectedItemIndex) ColorBlue else ColorGray,
                            contentDescription = item.title
                        )
                    }
                },
                label = {
                    Text(
                        fontFamily = FontFamily(Font(Res.font.Inter_Medium)),
                        text = item.title,
                        color = if (index == selectedItemIndex) ColorBlue else ColorGray
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
    onOpenBottomSheet: (BottomSheetContent) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.Home.route
    ) {
        composable(ScreenRoutes.Home.route) {
            bottomNavState.value = true
//            HomeScreen(navController, bottomNavState)
            HomeScreen(onOpenBottomSheet = onOpenBottomSheet)
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