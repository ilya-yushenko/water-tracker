package com.tide.db.model

data class DrinkIntakeRecord(
    val id: String,
    val drinkType: DrinkType,
    val name: String,
    val amount: Int,
    val createdAt: Long,
    val updatedAt: Long
)