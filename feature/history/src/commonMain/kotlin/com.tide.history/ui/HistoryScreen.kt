package com.tide.history.ui

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
import com.tide.history.di.historyDI
import com.tide.history.ui.components.StoryItem
import com.tide.history.viewmodel.HistoryViewModel
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance
import watertracker.feature.history.generated.resources.Res
import watertracker.feature.history.generated.resources.history_screen_story
import watertracker.feature.history.generated.resources.history_screen_title

@Composable
fun HistoryScreen() {
    val colors = appColorPalette()

    val viewModel: HistoryViewModel by historyDI.instance()
    val storyLog = viewModel.storyLog


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

                repeat(storyLog.value.size) { index ->
                    StoryItem(
                        label = storyLog.value[index].label,
                        volume = storyLog.value[index].volume,
                        time = storyLog.value[index].time,
                        iconRes = storyLog.value[index].iconRes,
                        onClickDelete = {
                            viewModel.deleteIntakeRecord(storyLog.value[index].recordId)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}