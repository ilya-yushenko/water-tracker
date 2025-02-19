package com.yushenko.watertracker.ui.screens.home

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
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
import com.yushenko.watertracker.ui.screens.root.BottomSheetContent
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
    onOpenBottomSheet: (BottomSheetContent) -> Unit,
) {
    val drinks = listOf(
        DrinkModel("Water", "250 ml", Res.drawable.ic_quick_water),
        DrinkModel("Coffee", "200 ml", Res.drawable.ic_quick_coffee),
        DrinkModel("Tea", "200 ml", Res.drawable.ic_quick_tea),
        DrinkModel("Smoothie", "200 ml", Res.drawable.ic_quick_smoothie),
        DrinkModel("Juice", "200 ml", Res.drawable.ic_quick_juice),
        DrinkModel("Milk", "200 ml", Res.drawable.ic_quick_milk),
        DrinkModel("Soda", "200 ml", Res.drawable.ic_quick_soda)
    )

    val storyLog = listOf(
        StoryModel("Wather", "300 ml", "14:30", Res.drawable.ic_quick_water),
        StoryModel("Coffee", "200 ml", "11:15", Res.drawable.ic_quick_coffee),
        StoryModel("Tea", "250 ml", "09:00", Res.drawable.ic_quick_tea),
        StoryModel("Wather", "450 ml", "08:00", Res.drawable.ic_quick_water)
    )

    val selectedDrink = remember { mutableStateOf<DrinkModel?>(null) }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val lazyListState = rememberLazyListState()

        val maxToolbarHeight = maxHeight * 0.65f
        val minToolbarHeight = 106.dp
        val maxToolbarPx = with(LocalDensity.current) { maxToolbarHeight.roundToPx().toFloat() }
        val minToolbarPx = with(LocalDensity.current) { minToolbarHeight.roundToPx().toFloat() }
        val maxCollapseRange = maxToolbarPx - minToolbarPx
        val scrollOffset = remember {
            derivedStateOf {
                val firstItemIndex = lazyListState.firstVisibleItemIndex
                val firstItemOffset = lazyListState.firstVisibleItemScrollOffset
                (firstItemIndex * lazyListState.layoutInfo.viewportSize.height + firstItemOffset).toFloat()
            }
        }
        val collapseFraction = (scrollOffset.value / maxCollapseRange).coerceIn(0f, 1f)
        val currentToolbarHeightDp =
            with(LocalDensity.current) { (maxToolbarPx - (maxCollapseRange * collapseFraction)).toDp() }

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

        Box(modifier = Modifier.fillMaxSize().background(ColorBackground)) {
            LazyColumn(state = lazyListState, modifier = Modifier.fillMaxSize()) {
                item { Spacer(modifier = Modifier.height(maxToolbarHeight)) }
                item {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth().background(ColorWhite),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        items(drinks, key = { it.label }) { model ->
                            DrinkItem(
                                label = model.label,
                                volume = model.volume,
                                iconRes = model.iconRes,
                                onClick = {
                                    selectedDrink.value = model
                                    selectedDrink.value?.let {
                                        onOpenBottomSheet(BottomSheetContent.TrackHydration(it))
                                    }
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
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    storyLog.forEach { model ->
                        StoryItem(
                            label = model.label,
                            volume = model.volume,
                            time = model.time,
                            iconRes = model.iconRes,
                            onClickDelete = {}
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

            HeaderScreen(
                label = stringResource(Res.string.home_screen_title),
                modifier = Modifier.height(currentToolbarHeightDp).fillMaxWidth()
            ) {
                val circleScale = 1f - 0.5f * collapseFraction
                val circleAlpha = 1f - collapseFraction
                Text(
                    text = "15 February 2025",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily(Font(Res.font.Inter_Medium)),
                    color = ColorWhite80,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                        .graphicsLayer { alpha = circleAlpha }
                )
                Spacer(modifier = Modifier.weight(1f))
                WaterCircularIndicator(
                    modifier = Modifier.padding(vertical = 40.dp).fillMaxWidth(0.65f)
                        .align(Alignment.CenterHorizontally).graphicsLayer {
                            scaleX = circleScale
                            scaleY = circleScale
                            alpha = circleAlpha
                        },
                    currentWater = 1200, targetWater = 2500
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            FloatingActionButton(
                onClick = {
                    if (fabVisible) {
                        selectedDrink.value = drinks[0]
                        selectedDrink.value?.let {
                            onOpenBottomSheet(BottomSheetContent.TrackHydration(it))
                        }
                    }
                },
                backgroundColor = ColorBlue,
                contentColor = ColorWhite,
                modifier = Modifier.size(64.dp).align(Alignment.BottomCenter).offset(y = (-8).dp)
                    .graphicsLayer {
                        alpha = fabAlpha
                        translationY = fabTranslationY
                    }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    }
}