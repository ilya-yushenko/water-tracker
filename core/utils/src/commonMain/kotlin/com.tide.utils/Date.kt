package com.tide.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getFormattedCurrentDate(): String {
    val date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    val month = date.month.name.lowercase().replaceFirstChar { it.uppercase() }
    return "${date.dayOfMonth} $month ${date.year}"
}

fun getFormattedTime(epochMillis: Long): String {
    val instant = Instant.fromEpochMilliseconds(epochMillis)
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val hours = localDateTime.hour.toString().padStart(2, '0')
    val minutes = localDateTime.minute.toString().padStart(2, '0')
    return "$hours:$minutes"
}