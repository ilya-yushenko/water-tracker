package com.yushenko.watertracker.ui.screens.history

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yushenko.watertracker.theme.ColorBackground
import com.yushenko.watertracker.theme.ColorBlack
import com.yushenko.watertracker.ui.components.HeaderScreen
import com.yushenko.watertracker.ui.components.StoryItem
import com.yushenko.watertracker.ui.components.StoryModel
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import watertracker.composeapp.generated.resources.Inter_SemiBold
import watertracker.composeapp.generated.resources.Res
import watertracker.composeapp.generated.resources.history_screen_title
import watertracker.composeapp.generated.resources.home_screen_story
import watertracker.composeapp.generated.resources.ic_quick_coffee
import watertracker.composeapp.generated.resources.ic_quick_tea
import watertracker.composeapp.generated.resources.ic_quick_water

@Composable
fun HistoryScreen() {

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
    ) {
        HeaderScreen(
            label = stringResource(Res.string.history_screen_title),
            modifier = Modifier.height(106.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorBackground)
        ) {
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
}