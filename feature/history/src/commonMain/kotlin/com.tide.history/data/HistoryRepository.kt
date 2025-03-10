package com.tide.history.data

import com.tide.history.model.HistoryDrinkIntakeRecord
import com.tide.history.model.HistoryDrinkType

interface HistoryRepository {
    fun getIntakeRecordsByPage(page: Int, pageSize: Int): List<HistoryDrinkIntakeRecord>
    suspend fun deleteIntakeRecord(recordId: String)
    suspend fun updateIntakeRecord(
        recordId: String,
        newDrinkType: HistoryDrinkType,
        newName: String,
        newAmount: Int,
        newUpdatedAt: Long
    )
}