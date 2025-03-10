package com.tide.history.model

import com.tide.db.model.DrinkIntakeRecord

data class HistoryDrinkIntakeRecord(
    val id: String,
    val drinkType: HistoryDrinkType,
    val name: String,
    val amount: Int,
    val createdAt: Long,
    val updatedAt: Long
)

fun HistoryDrinkIntakeRecord.fromHistory() =
    DrinkIntakeRecord(
        id = id,
        drinkType = drinkType.fromHistory(),
        name = name,
        amount = amount,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

fun DrinkIntakeRecord.toHistory() =
    HistoryDrinkIntakeRecord(
        id = id,
        drinkType = drinkType.toHistory(),
        name = name,
        amount = amount,
        createdAt = createdAt,
        updatedAt = updatedAt
    )