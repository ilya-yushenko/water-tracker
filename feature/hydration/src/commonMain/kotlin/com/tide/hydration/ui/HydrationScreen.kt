package com.tide.hydration.ui

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tide.common.AppTypography
import com.tide.common.BottomSheetType
import com.tide.common.appColorPalette
import com.tide.common.components.HeaderScreen
import com.tide.hydration.di.hydrationDI
import com.tide.hydration.model.toIconRes
import com.tide.hydration.ui.components.DrinkItem
import com.tide.hydration.ui.components.StoryEmptyItem
import com.tide.hydration.ui.components.StoryItem
import com.tide.hydration.ui.components.WaterCircularIndicator
import com.tide.hydration.viewmodel.HydrationViewModel
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance
import watertracker.feature.hydration.generated.resources.Res
import watertracker.feature.hydration.generated.resources.home_screen_story
import watertracker.feature.hydration.generated.resources.home_screen_title


@Composable
fun HydrationScreen(
//    navController: NavController,
    onOpenBottomSheet: (BottomSheetType) -> Unit
) {
    val colors = appColorPalette()

    val viewModel: HydrationViewModel by hydrationDI.instance()
    val dailyIntake = viewModel.dailyIntake
    val currentData = viewModel.currentData
    val drinks = viewModel.drinks
    val storyLog = viewModel.storyLog

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

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(state = lazyListState, modifier = Modifier.fillMaxSize()) {
                item { Spacer(modifier = Modifier.height(maxToolbarHeight)) }
                item {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth().background(colors.surface),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        items(drinks.value, key = { it.label }) { model ->
                            DrinkItem(
                                label = model.label,
                                volume = model.volume,
                                iconRes = model.drinkType.toIconRes(),
                                onClick = {
                                    viewModel.addIntakeRecord(
                                        model.drinkType,
                                        model.label,
                                        model.volume
                                    )
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
                        fontFamily = AppTypography.semiBold(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    if (storyLog.value.isEmpty()) {
                        StoryEmptyItem(onClick = {
                            onOpenBottomSheet(BottomSheetType.TrackHydration)
                        })
                        Spacer(modifier = Modifier.height(32.dp))
                    } else {
                        storyLog.value.forEach { model ->
                            StoryItem(
                                label = model.label,
                                volume = model.volume,
                                time = model.time,
                                iconRes = model.iconRes,
                                onClickDelete = { viewModel.deleteIntakeRecord(model.recordId) }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
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
                    text = currentData.value,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    fontFamily = AppTypography.medium(),
                    color = colors.headerText80,
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
                    currentWater = dailyIntake.value,
                    targetWater = 2500
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            FloatingActionButton(
                onClick = {
                    if (fabVisible) {
                        onOpenBottomSheet(BottomSheetType.TrackHydration)
                    }
                },
                backgroundColor = colors.blue,
                contentColor = colors.headerText,
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