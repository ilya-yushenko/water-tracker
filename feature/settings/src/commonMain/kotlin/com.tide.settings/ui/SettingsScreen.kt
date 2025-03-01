package com.tide.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tide.common.components.HeaderScreen
import com.tide.settings.ui.components.SettingsChangeItem
import com.tide.settings.ui.components.SettingsGroupItem
import com.tide.settings.ui.components.SettingsSwitchItem
import org.jetbrains.compose.resources.stringResource
import watertracker.feature.settings.generated.resources.Res
import watertracker.feature.settings.generated.resources.ic_settings_goal
import watertracker.feature.settings.generated.resources.ic_settings_info
import watertracker.feature.settings.generated.resources.ic_settings_privacy
import watertracker.feature.settings.generated.resources.ic_settings_reminder
import watertracker.feature.settings.generated.resources.ic_settings_theme
import watertracker.feature.settings.generated.resources.settings_screen_about
import watertracker.feature.settings.generated.resources.settings_screen_app_version
import watertracker.feature.settings.generated.resources.settings_screen_current_goal
import watertracker.feature.settings.generated.resources.settings_screen_daily_goal
import watertracker.feature.settings.generated.resources.settings_screen_dark_theme
import watertracker.feature.settings.generated.resources.settings_screen_language
import watertracker.feature.settings.generated.resources.settings_screen_metric_imperial
import watertracker.feature.settings.generated.resources.settings_screen_personalization
import watertracker.feature.settings.generated.resources.settings_screen_privacy_policy
import watertracker.feature.settings.generated.resources.settings_screen_reminder_interval
import watertracker.feature.settings.generated.resources.settings_screen_title

@Composable
fun SettingsScreen() {

    val checked = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        HeaderScreen(
            label = stringResource(Res.string.settings_screen_title),
            modifier = Modifier.height(106.dp)
        )

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