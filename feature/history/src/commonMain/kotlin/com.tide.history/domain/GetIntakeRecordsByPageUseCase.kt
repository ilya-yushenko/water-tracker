package com.tide.history.domain

import com.tide.history.data.HistoryRepository
import com.tide.history.model.HistoryDrinkIntakeRecord

class GetIntakeRecordsByPageUseCase(private val repository: HistoryRepository) {
    operator fun invoke(page: Int, pageSize: Int): List<HistoryDrinkIntakeRecord> {
        return repository.getIntakeRecordsByPage(page, pageSize)
    }
}