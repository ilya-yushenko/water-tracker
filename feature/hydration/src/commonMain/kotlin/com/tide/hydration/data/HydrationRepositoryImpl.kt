package com.tide.hydration.data

import com.tide.db.storage.DrinkIntakeStorage
import com.tide.hydration.model.HydrationDrinkIntakeRecord
import com.tide.hydration.model.HydrationDrinkType
import com.tide.hydration.model.fromHydration
import com.tide.hydration.model.toHydration

class HydrationRepositoryImpl(
    private val intakeStorage: DrinkIntakeStorage
) : HydrationRepository {

    override suspend fun addIntakeRecord(drinkType: HydrationDrinkType, name: String, amount: Int) {
        intakeStorage.addIntakeRecord(
            drinkType = drinkType.fromHydration(),
            name = name,
            amount = amount
        )
    }

    override fun getIntakeRecordsByPeriod(
        startOfDay: Long,
        endOfDay: Long
    ): List<HydrationDrinkIntakeRecord> {
        return intakeStorage.getIntakeRecordsByPeriod(startOfDay, endOfDay)
            .map { model -> model.toHydration() }
    }

    override suspend fun deleteIntakeRecord(recordId: String) {
        intakeStorage.deleteIntakeRecord(recordId)
    }
}