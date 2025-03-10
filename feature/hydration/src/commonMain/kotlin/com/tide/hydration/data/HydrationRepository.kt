package com.tide.hydration.data

import com.tide.hydration.model.HydrationDrinkIntakeRecord
import com.tide.hydration.model.HydrationDrinkType

interface HydrationRepository {
    suspend fun addIntakeRecord(drinkType: HydrationDrinkType, name: String, amount: Int)
    fun getIntakeRecordsByPeriod(startOfDay: Long, endOfDay: Long): List<HydrationDrinkIntakeRecord>
    suspend fun deleteIntakeRecord(recordId: String)
}