package com.tide.hydration.domain

import com.tide.hydration.data.HydrationRepository
import com.tide.hydration.model.HydrationDrinkIntakeRecord
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class GetDrinkIntakeRecordsByDateUseCase(private val repository: HydrationRepository) {
    operator fun invoke(timestamp: Long): Flow<List<HydrationDrinkIntakeRecord>> {
        val instant = Instant.fromEpochMilliseconds(timestamp)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        val startOfDay = LocalDateTime(
            year = localDateTime.year,
            monthNumber = localDateTime.monthNumber,
            dayOfMonth = localDateTime.dayOfMonth,
            hour = 0,
            minute = 0,
            second = 0,
            nanosecond = 0
        ).toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()

        val endOfDay = LocalDateTime(
            year = localDateTime.year,
            monthNumber = localDateTime.monthNumber,
            dayOfMonth = localDateTime.dayOfMonth,
            hour = 23,
            minute = 59,
            second = 59,
            nanosecond = 999_999_999
        ).toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()

        return repository.getIntakeRecordsByPeriod(startOfDay, endOfDay)
    }
}