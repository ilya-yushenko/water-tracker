package com.tide.hydration.domain

import com.tide.hydration.data.HydrationRepository

class DeleteDrinkIntakeRecordUseCase(private val repository: HydrationRepository) {
    suspend operator fun invoke(recordId: String) {
        repository.deleteIntakeRecord(recordId)
    }
}