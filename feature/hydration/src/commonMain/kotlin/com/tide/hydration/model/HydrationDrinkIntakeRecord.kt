package com.tide.hydration.model

import com.tide.db.model.DrinkIntakeRecord

data class HydrationDrinkIntakeRecord(
    val id: String,
    val drinkType: HydrationDrinkType,
    val name: String,
    val amount: Int,
    val createdAt: Long,
    val updatedAt: Long
)

fun HydrationDrinkIntakeRecord.fromHydration() =
    DrinkIntakeRecord(
        id = id,
        drinkType = drinkType.fromHydration(),
        name = name,
        amount = amount,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

fun DrinkIntakeRecord.toHydration() =
    HydrationDrinkIntakeRecord(
        id = id,
        drinkType = drinkType.toHydration(),
        name = name,
        amount = amount,
        createdAt = createdAt,
        updatedAt = updatedAt
    )