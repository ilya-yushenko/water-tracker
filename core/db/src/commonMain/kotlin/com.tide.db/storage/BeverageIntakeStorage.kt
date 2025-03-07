package com.tide.db.storage

import com.tide.db.model.BeverageRecord
import com.tide.db.model.DrinkType

interface BeverageIntakeStorage {
    suspend fun addIntakeRecord(record: BeverageRecord)
    fun getIntakeRecords(): List<BeverageRecord>
    fun getIntakeRecordsByPage(page: Int, pageSize: Int): List<BeverageRecord>
    fun getIntakeRecordsByPeriod(startTimestamp: Long, endTimestamp: Long): List<BeverageRecord>
    fun getIntakeRecordsByDate(dayStartTimestamp: Long): List<BeverageRecord>
    fun getIntakeRecordsByDrinkType(drinkType: DrinkType): List<BeverageRecord>
    suspend fun deleteIntakeRecord(recordId: String)
    suspend fun updateIntakeRecord(
        recordId: String,
        newDrinkType: DrinkType,
        newName: String,
        newAmount: Int,
        newUpdatedAt: Long
    )
}