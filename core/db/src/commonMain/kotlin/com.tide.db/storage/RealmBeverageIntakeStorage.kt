package com.tide.db.storage

import com.tide.db.DatabaseProvider
import com.tide.db.entity.BeverageEntity
import com.tide.db.model.BeverageRecord
import com.tide.db.model.DrinkType
import io.realm.kotlin.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.min

class RealmBeverageIntakeStorage(
    private val databaseProvider: DatabaseProvider
) : BeverageIntakeStorage {

    private val database: Realm get() = databaseProvider.getRealmInstance()

    /**
     * Saving a new record
     */
    override suspend fun addIntakeRecord(record: BeverageRecord) {
        withContext(Dispatchers.Default) {
            database.write {
                val entity = BeverageEntity().apply {
                    drinkType = record.drinkType.name
                    name = record.name
                    amount = record.amount
                    createdAt = record.createdAt
                    updatedAt = record.updatedAt
                }
                copyToRealm(entity)
            }
        }
    }

    /**
     *  Get all records
     * */
    override fun getIntakeRecords(): List<BeverageRecord> {
        return database.query(BeverageEntity::class).find().map { entity ->
            BeverageRecord(
                id = entity.id,
                drinkType = DrinkType.valueOf(entity.drinkType),
                name = entity.name,
                amount = entity.amount,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            )
        }
    }

    /**
     * Getting records by pagination: returns "page" from results
     */
    override fun getIntakeRecordsByPage(page: Int, pageSize: Int): List<BeverageRecord> {
        val allRecords = database.query(BeverageEntity::class).find()
        val fromIndex = page * pageSize
        if (fromIndex >= allRecords.size) return emptyList()
        val toIndex = min(fromIndex + pageSize, allRecords.size)
        return allRecords.subList(fromIndex, toIndex).map { entity ->
            BeverageRecord(
                id = entity.id,
                drinkType = DrinkType.valueOf(entity.drinkType),
                name = entity.name,
                amount = entity.amount,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            )
        }
    }

    /**
     * Get records for the specified period (start and end of the period in milliseconds)
     */
    override fun getIntakeRecordsByPeriod(
        startTimestamp: Long,
        endTimestamp: Long
    ): List<BeverageRecord> {
        return database.query(
            BeverageEntity::class,
            "createdAt >= $0 AND createdAt <= $1",
            startTimestamp,
            endTimestamp
        ).find().map { entity ->
            BeverageRecord(
                id = entity.id,
                drinkType = DrinkType.valueOf(entity.drinkType),
                name = entity.name,
                amount = entity.amount,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            )
        }
    }

    /**
     * Get records for a specific day (assuming start of day in milliseconds)
     *
     * Assumes day period = 24 hours (86400000 ms)
     */
    override fun getIntakeRecordsByDate(dayStartTimestamp: Long): List<BeverageRecord> {
        val dayEnd = dayStartTimestamp + 86400000 - 1
        return getIntakeRecordsByPeriod(dayStartTimestamp, dayEnd)
    }

    /**
     * Get records by drink type
     */
    override fun getIntakeRecordsByDrinkType(drinkType: DrinkType): List<BeverageRecord> {
        return database.query(
            BeverageEntity::class,
            "drinkType == $0",
            drinkType.name
        ).find().map { entity ->
            BeverageRecord(
                id = entity.id,
                drinkType = DrinkType.valueOf(entity.drinkType),
                name = entity.name,
                amount = entity.amount,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            )
        }
    }

    /**
     *  Delete record by id
     * */
    override suspend fun deleteIntakeRecord(recordId: String) {
        withContext(Dispatchers.Default) {
            database.write {
                val record = query(BeverageEntity::class).find().firstOrNull { it.id == recordId }
                if (record != null) {
                    delete(record)
                }
            }
        }
    }

    /**
     *  Update record by id
     * */
    override suspend fun updateIntakeRecord(
        recordId: String,
        newDrinkType: DrinkType,
        newName: String,
        newAmount: Int,
        newUpdatedAt: Long
    ) {
        withContext(Dispatchers.Default) {
            database.write {
                val record = query(BeverageEntity::class).find().firstOrNull { it.id == recordId }
                if (record != null) {
                    record.drinkType = newDrinkType.name
                    record.name = newName
                    record.amount = newAmount
                    record.updatedAt = newUpdatedAt
                }
            }
        }
    }
}