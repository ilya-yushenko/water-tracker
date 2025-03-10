package com.tide.history.domain

import com.tide.history.data.HistoryRepository
import com.tide.history.model.HistoryDrinkType
import kotlinx.datetime.Clock

class UpdateIntakeRecordUseCase(private val repository: HistoryRepository) {
    suspend operator fun invoke(
        recordId: String,
        newDrinkType: HistoryDrinkType,
        newName: String,
        newAmount: Int,
    ) {
        val now = Clock.System.now().toEpochMilliseconds()
        repository.updateIntakeRecord(recordId, newDrinkType, newName, newAmount, now)
    }
}