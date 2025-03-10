package com.tide.db.storage

import com.tide.db.model.DrinkIntakeRecord
import com.tide.db.model.DrinkType

interface DrinkIntakeStorage {
    suspend fun addIntakeRecord(drinkType: DrinkType, name: String, amount: Int)
    fun getIntakeRecords(): List<DrinkIntakeRecord>
    fun getIntakeRecordsByPage(page: Int, pageSize: Int): List<DrinkIntakeRecord>
    fun getIntakeRecordsByPeriod(startOfDay: Long, endOfDay: Long): List<DrinkIntakeRecord>
    fun getIntakeRecordsByDrinkType(drinkType: DrinkType): List<DrinkIntakeRecord>
    suspend fun deleteIntakeRecord(recordId: String)
    suspend fun updateIntakeRecord(
        recordId: String,
        newDrinkType: DrinkType,
        newName: String,
        newAmount: Int,
        newUpdatedAt: Long
    )
}