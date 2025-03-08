package com.tide.hydration.domain

import com.tide.hydration.data.HydrationRepository
import com.tide.hydration.model.HydrationDrinkIntakeRecord

class GetDrinkIntakeRecordsByDateUseCase(private val repository: HydrationRepository) {
    operator fun invoke(dayStartTimestamp: Long): List<HydrationDrinkIntakeRecord> {
        return repository.getIntakeRecordsByDate(dayStartTimestamp)
    }
}