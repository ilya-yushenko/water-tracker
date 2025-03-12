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
import androidx.compose.runtime.remember
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
import com.tide.utils.getFormattedTime
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource
import org.kodein.di.instance
import watertracker.feature.history.generated.resources.Res
import watertracker.feature.history.generated.resources.history_screen_story
import watertracker.feature.history.generated.resources.history_screen_title
import watertracker.feature.history.generated.resources.history_screen_yesterday

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

        val groupedStories = remember(storyLog.value) {
            storyLog.value
                .sortedByDescending { it.time }
                .groupBy { timeToDateGroup(it.time) }
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            groupedStories.forEach { (dateGroup, stories) ->
                item {
                    Text(
                        text = getDateGroupTitle(dateGroup),
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
                }

                items(stories.size) { index ->
                    StoryItem(
                        label = stories[index].label,
                        volume = stories[index].volume,
                        time = getFormattedTime(stories[index].time),
                        iconRes = stories[index].iconRes,
                        onClickDelete = {
                            viewModel.deleteIntakeRecord(stories[index].recordId)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

private fun timeToDateGroup(epochMillis: Long): Long {
    return Instant.fromEpochMilliseconds(epochMillis)
        .toLocalDateTime(TimeZone.currentSystemDefault()).date
        .atStartOfDayIn(TimeZone.currentSystemDefault())
        .toEpochMilliseconds()
}

@Composable
private fun getDateGroupTitle(epochMillis: Long): String {
    val instant = Instant.fromEpochMilliseconds(epochMillis)
    val date = instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    return when (date) {
        today -> stringResource(Res.string.history_screen_story)
        today.minus(1, DateTimeUnit.DAY) -> stringResource(Res.string.history_screen_yesterday)
        else -> {
            val month = date.month.name.lowercase().replaceFirstChar { it.uppercase() }
            "${date.dayOfMonth} $month ${date.year}"
        }
    }
}