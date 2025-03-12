package com.tide.history.data

import com.tide.db.storage.DrinkIntakeStorage
import com.tide.history.model.HistoryDrinkIntakeRecord
import com.tide.history.model.HistoryDrinkType
import com.tide.history.model.fromHistory
import com.tide.history.model.toHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class HistoryRepositoryImpl(
    private val intakeStorage: DrinkIntakeStorage
) : HistoryRepository {

    override fun getIntakeRecordsByPage(page: Int, pageSize: Int): Flow<List<HistoryDrinkIntakeRecord>> {
        return intakeStorage.getIntakeRecordsByPage(page, pageSize)
            .map { list -> list.map { model -> model.toHistory() } }
    }

    override suspend fun deleteIntakeRecord(recordId: String) {
        intakeStorage.deleteIntakeRecord(recordId)
    }

    override suspend fun updateIntakeRecord(
        recordId: String,
        newDrinkType: HistoryDrinkType,
        newName: String,
        newAmount: Int,
        newUpdatedAt: Long
    ) {
        intakeStorage.updateIntakeRecord(
            recordId = recordId,
            newDrinkType = newDrinkType.fromHistory(),
            newName = newName,
            newAmount = newAmount,
            newUpdatedAt = newUpdatedAt
        )
    }
}