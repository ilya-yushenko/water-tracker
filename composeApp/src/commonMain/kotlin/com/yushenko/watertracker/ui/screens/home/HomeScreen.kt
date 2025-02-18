package com.yushenko.watertracker.ui.screens.home

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yushenko.watertracker.theme.ColorBackground
import com.yushenko.watertracker.theme.ColorBlack
import com.yushenko.watertracker.theme.ColorBlue
import com.yushenko.watertracker.theme.ColorWhite
import com.yushenko.watertracker.theme.ColorWhite80
import com.yushenko.watertracker.ui.components.DrinkItem
import com.yushenko.watertracker.ui.components.DrinkModel
import com.yushenko.watertracker.ui.components.HeaderScreen
import com.yushenko.watertracker.ui.components.StoryItem
import com.yushenko.watertracker.ui.components.StoryModel
import com.yushenko.watertracker.ui.components.WaterCircularIndicator
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import watertracker.composeapp.generated.resources.Inter_Medium
import watertracker.composeapp.generated.resources.Inter_SemiBold
import watertracker.composeapp.generated.resources.Res
import watertracker.composeapp.generated.resources.home_screen_story
import watertracker.composeapp.generated.resources.home_screen_title
import watertracker.composeapp.generated.resources.ic_quick_coffee
import watertracker.composeapp.generated.resources.ic_quick_juice
import watertracker.composeapp.generated.resources.ic_quick_milk
import watertracker.composeapp.generated.resources.ic_quick_smoothie
import watertracker.composeapp.generated.resources.ic_quick_soda
import watertracker.composeapp.generated.resources.ic_quick_tea
import watertracker.composeapp.generated.resources.ic_quick_water

@Composable
fun HomeScreen(
//    navController: NavController,
    bottomNavState: MutableState<Boolean> = mutableStateOf(false),
) {

    val drinks = listOf(
        DrinkModel(
            label = "Water",
            volume = "250 ml",
            iconRes = Res.drawable.ic_quick_water
        ),
        DrinkModel(
            label = "Coffee",
            volume = "200 ml",
            iconRes = Res.drawable.ic_quick_coffee
        ),
        DrinkModel(
            label = "Tea",
            volume = "200 ml",
            iconRes = Res.drawable.ic_quick_tea
        ),
        DrinkModel(
            label = "Smoothie",
            volume = "200 ml",
            iconRes = Res.drawable.ic_quick_smoothie
        ),
        DrinkModel(
            label = "Juice",
            volume = "200 ml",
            iconRes = Res.drawable.ic_quick_juice
        ),
        DrinkModel(
            label = "Milk",
            volume = "200 ml",
            iconRes = Res.drawable.ic_quick_milk
        ),
        DrinkModel(
            label = "Soda",
            volume = "200 ml",
            iconRes = Res.drawable.ic_quick_soda
        ),
    )

    val storyLog: List<StoryModel> = listOf(
        StoryModel(
            label = "Wather",
            volume = "300 ml",
            time = "14:30",
            iconRes = Res.drawable.ic_quick_water
        ),
        StoryModel(
            label = "Coffee",
            volume = "200 ml",
            time = "11:15",
            iconRes = Res.drawable.ic_quick_coffee
        ),
        StoryModel(
            label = "Tea",
            volume = "250 ml",
            time = "09:00",
            iconRes = Res.drawable.ic_quick_tea
        ),
        StoryModel(
            label = "Wather",
            volume = "450 ml",
            time = "08:00",
            iconRes = Res.drawable.ic_quick_water
        )
    )

//    val isSheetVisible = remember { mutableStateOf(true) }
//    val selectedDrink = remember { mutableStateOf<DrinkModel?>(drinks[0]) }

    val isSheetVisible = remember { mutableStateOf(false) }
    val selectedDrink = remember { mutableStateOf<DrinkModel?>(null) }

    bottomNavState.value = !isSheetVisible.value

    val lazyListState = rememberLazyListState()
    var previousOffset by remember { mutableStateOf(0) }
    var fabVisible by remember { mutableStateOf(true) }

    LaunchedEffect(lazyListState.firstVisibleItemScrollOffset) {
        val currentOffset = lazyListState.firstVisibleItemScrollOffset
        fabVisible = when {
            currentOffset < previousOffset -> true
            currentOffset - previousOffset > 20 -> false
            else -> fabVisible
        }
        previousOffset = currentOffset
    }

    val fabAlpha by animateFloatAsState(
        targetValue = if (fabVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )

    val fabTranslationY by animateFloatAsState(
        targetValue = if (fabVisible) 0f else 100f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorBackground)
        ) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    HeaderScreen(stringResource(Res.string.home_screen_title)) {
                        Text(
                            text = "15 February 2025",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily(Font(Res.font.Inter_Medium)),
                            color = ColorWhite80,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                        WaterCircularIndicator(
                            modifier = Modifier
                                .padding(vertical = 40.dp)
                                .fillMaxWidth(0.65f)
                                .align(Alignment.CenterHorizontally),
                            currentWater = 1200, targetWater = 2500
                        )
                    }
                }

                item {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(ColorWhite),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        items(drinks) { model ->
                            DrinkItem(
                                label = model.label,
                                volume = model.volume,
                                iconRes = model.iconRes,
                                onClick = {
                                    selectedDrink.value = model
                                    isSheetVisible.value = true
                                }
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = stringResource(Res.string.home_screen_story),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start,
                        fontFamily = FontFamily(Font(Res.font.Inter_SemiBold)),
                        color = ColorBlack,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    repeat(storyLog.size) { index ->
                        StoryItem(
                            label = storyLog[index].label,
                            volume = storyLog[index].volume,
                            time = storyLog[index].time,
                            iconRes = storyLog[index].iconRes,
                            onClickDelete = {}
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {
                selectedDrink.value = drinks[0]
                isSheetVisible.value = true
            },
            backgroundColor = ColorBlue,
            contentColor = ColorWhite,
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-8).dp)
                .graphicsLayer {
                    alpha = fabAlpha
                    translationY = fabTranslationY
                }
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }
    }

    if (isSheetVisible.value) {
        AddHydrationBottomSheet(
            selectedDrink.value!!,
            onAddClick = {

            },
            onDismiss = {
                isSheetVisible.value = false
            }
        )
    }
}