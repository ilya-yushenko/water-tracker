package com.yushenko.watertracker.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.yushenko.watertracker.theme.ColorBackground
import com.yushenko.watertracker.theme.ColorBlue
import com.yushenko.watertracker.theme.ColorBlueGradient
import com.yushenko.watertracker.ui.components.SettingsChangeItem
import com.yushenko.watertracker.ui.components.SettingsGroupItem
import com.yushenko.watertracker.ui.components.SettingsSwitchItem
import com.yushenko.watertracker.ui.components.TitleScreen
import org.jetbrains.compose.resources.stringResource
import watertracker.composeapp.generated.resources.Res
import watertracker.composeapp.generated.resources.ic_settings_goal
import watertracker.composeapp.generated.resources.ic_settings_info
import watertracker.composeapp.generated.resources.ic_settings_privacy
import watertracker.composeapp.generated.resources.ic_settings_reminder
import watertracker.composeapp.generated.resources.ic_settings_theme
import watertracker.composeapp.generated.resources.settings_screen_about
import watertracker.composeapp.generated.resources.settings_screen_app_version
import watertracker.composeapp.generated.resources.settings_screen_current_goal
import watertracker.composeapp.generated.resources.settings_screen_daily_goal
import watertracker.composeapp.generated.resources.settings_screen_dark_theme
import watertracker.composeapp.generated.resources.settings_screen_language
import watertracker.composeapp.generated.resources.settings_screen_metric_imperial
import watertracker.composeapp.generated.resources.settings_screen_personalization
import watertracker.composeapp.generated.resources.settings_screen_privacy_policy
import watertracker.composeapp.generated.resources.settings_screen_reminder_interval
import watertracker.composeapp.generated.resources.settings_screen_title

@Composable
fun SettingsScreen() {

    val checked = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(ColorBlueGradient, ColorBlue),
                        start = Offset(0f, 0f),
                        end = Offset(1000f, 0f)
                    )
                )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TitleScreen(
                    text = stringResource(Res.string.settings_screen_title)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 36.dp)
        ) {
            item {
                SettingsGroupItem(
                    title = stringResource(Res.string.settings_screen_current_goal)
                ) {
                    SettingsChangeItem(
                        iconRes = Res.drawable.ic_settings_goal,
                        label = stringResource(Res.string.settings_screen_daily_goal),
                        value = "2500 ml",
                        onClickDelete = {}
                    )
                    SettingsChangeItem(
                        iconRes = Res.drawable.ic_settings_reminder,
                        label = stringResource(Res.string.settings_screen_reminder_interval),
                        value = "2 hours",
                        onClickDelete = {}
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                SettingsGroupItem(
                    title = stringResource(Res.string.settings_screen_personalization)
                ) {
                    SettingsSwitchItem(
                        iconRes = Res.drawable.ic_settings_theme,
                        label = stringResource(Res.string.settings_screen_dark_theme),
                        checked = checked
                    )
                    SettingsChangeItem(
                        iconRes = Res.drawable.ic_settings_goal,
                        label = stringResource(Res.string.settings_screen_language),
                        value = "English",
                        onClickDelete = {}
                    )
                    SettingsChangeItem(
                        iconRes = Res.drawable.ic_settings_reminder,
                        label = stringResource(Res.string.settings_screen_metric_imperial),
                        value = "Metric",
                        onClickDelete = {}
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                SettingsGroupItem(
                    title = stringResource(Res.string.settings_screen_about)
                ) {
                    SettingsChangeItem(
                        iconRes = Res.drawable.ic_settings_info,
                        label = stringResource(Res.string.settings_screen_app_version),
                        value = "2.1.0",
                        isArrow = false,
                        onClickDelete = {}
                    )

                    SettingsChangeItem(
                        iconRes = Res.drawable.ic_settings_privacy,
                        label = stringResource(Res.string.settings_screen_privacy_policy),
                        onClickDelete = {}
                    )
                }
            }
        }
    }
}