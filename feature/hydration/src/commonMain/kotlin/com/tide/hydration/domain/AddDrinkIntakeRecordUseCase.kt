package com.tide.hydration.domain

import com.tide.hydration.data.HydrationRepository
import com.tide.hydration.model.HydrationDrinkType

class AddDrinkIntakeRecordUseCase(private val repository: HydrationRepository) {
    suspend operator fun invoke(drinkType: HydrationDrinkType, name: String, amount: Int) {
        repository.addIntakeRecord(drinkType, name, amount)
    }
}