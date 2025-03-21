package com.tide.db.storage

import com.tide.db.DatabaseProvider
import com.tide.db.entity.DrinkIntakeRecordEntity
import com.tide.db.model.DrinkIntakeRecord
import com.tide.db.model.DrinkType
import io.realm.kotlin.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlin.math.min

class RealmDrinkIntakeStorage(
    private val databaseProvider: DatabaseProvider
) : DrinkIntakeStorage {

    private val database: Realm get() = databaseProvider.getRealmInstance()

    /**
     * Saving a new record
     */
    override suspend fun addIntakeRecord(drinkType: DrinkType, name: String, amount: Int) {
        val now = Clock.System.now().toEpochMilliseconds()
        withContext(Dispatchers.Default) {
            database.write {
                val entity = DrinkIntakeRecordEntity().apply {
                    this.drinkType = drinkType.name
                    this.name = name
                    this.amount = amount
                    createdAt = now
                    updatedAt = now
                }
                copyToRealm(entity)
            }
        }
    }

    /**
     *  Get all records
     * */
    override fun getIntakeRecords(): List<DrinkIntakeRecord> {
        return database.query(DrinkIntakeRecordEntity::class).find().map { entity ->
            DrinkIntakeRecord(
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
    override fun getIntakeRecordsByPage(page: Int, pageSize: Int): List<DrinkIntakeRecord> {
        val allRecords = database.query(DrinkIntakeRecordEntity::class).find()
        val fromIndex = page * pageSize
        if (fromIndex >= allRecords.size) return emptyList()
        val toIndex = min(fromIndex + pageSize, allRecords.size)
        return allRecords.subList(fromIndex, toIndex).map { entity ->
            DrinkIntakeRecord(
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
        startOfDay: Long,
        endOfDay: Long
    ): List<DrinkIntakeRecord> {
        return database.query(
            DrinkIntakeRecordEntity::class,
            "createdAt >= $0 AND createdAt <= $1",
            startOfDay,
            endOfDay
        ).find().map { entity ->
            DrinkIntakeRecord(
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
     * Get records by drink type
     */
    override fun getIntakeRecordsByDrinkType(drinkType: DrinkType): List<DrinkIntakeRecord> {
        return database.query(
            DrinkIntakeRecordEntity::class,
            "drinkType == $0",
            drinkType.name
        ).find().map { entity ->
            DrinkIntakeRecord(
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
                val record =
                    query(DrinkIntakeRecordEntity::class).find().firstOrNull { it.id == recordId }
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
                val record =
                    query(DrinkIntakeRecordEntity::class).find().firstOrNull { it.id == recordId }
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