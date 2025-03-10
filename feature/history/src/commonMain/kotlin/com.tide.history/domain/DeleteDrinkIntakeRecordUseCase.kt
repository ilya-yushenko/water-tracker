package com.tide.history.domain

import com.tide.history.data.HistoryRepository

class DeleteDrinkIntakeRecordUseCase(private val repository: HistoryRepository) {
    suspend operator fun invoke(recordId: String) {
        repository.deleteIntakeRecord(recordId)
    }
}