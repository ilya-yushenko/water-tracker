package com.tide.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tide.common.AppTypography
import com.tide.common.appColorPalette
import com.tide.common.components.HeaderScreen
import com.tide.settings.ui.components.StoryItem
import com.tide.settings.ui.components.StoryModel
import org.jetbrains.compose.resources.stringResource
import watertracker.feature.history.generated.resources.Res
import watertracker.feature.history.generated.resources.history_screen_story
import watertracker.feature.history.generated.resources.history_screen_title
import watertracker.feature.history.generated.resources.ic_quick_coffee
import watertracker.feature.history.generated.resources.ic_quick_tea
import watertracker.feature.history.generated.resources.ic_quick_water

@Composable
fun HistoryScreen() {
    val colors = appColorPalette()

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

    Column(modifier = Modifier.fillMaxSize()) {
        HeaderScreen(
            label = stringResource(Res.string.history_screen_title),
            modifier = Modifier.height(106.dp)
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(Res.string.history_screen_story),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontFamily = AppTypography.semiBold(),
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
}