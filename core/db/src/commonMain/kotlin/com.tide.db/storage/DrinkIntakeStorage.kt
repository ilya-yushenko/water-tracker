package com.tide.db.storage

import com.tide.db.model.DrinkIntakeRecord
import com.tide.db.model.DrinkType
import kotlinx.coroutines.flow.Flow

interface DrinkIntakeStorage {
    suspend fun addIntakeRecord(drinkType: DrinkType, name: String, amount: Int)
    fun getIntakeRecords(): Flow<List<DrinkIntakeRecord>>
    fun getIntakeRecordsByPage(page: Int, pageSize: Int): Flow<List<DrinkIntakeRecord>>
    fun getIntakeRecordsByPeriod(startOfDay: Long, endOfDay: Long): Flow<List<DrinkIntakeRecord>>
    fun getIntakeRecordsByDrinkType(drinkType: DrinkType): Flow<List<DrinkIntakeRecord>>
    suspend fun deleteIntakeRecord(recordId: String)
    suspend fun updateIntakeRecord(
        recordId: String,
        newDrinkType: DrinkType,
        newName: String,
        newAmount: Int,
        newUpdatedAt: Long
    )
}