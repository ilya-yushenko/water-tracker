package com.tide.hydration.data

import com.tide.hydration.model.HydrationDrinkIntakeRecord
import com.tide.hydration.model.HydrationDrinkType
import kotlinx.coroutines.flow.Flow

interface HydrationRepository {
    suspend fun addIntakeRecord(drinkType: HydrationDrinkType, name: String, amount: Int)
    fun getIntakeRecordsByPeriod(
        startOfDay: Long,
        endOfDay: Long
    ): Flow<List<HydrationDrinkIntakeRecord>>
    suspend fun deleteIntakeRecord(recordId: String)
}