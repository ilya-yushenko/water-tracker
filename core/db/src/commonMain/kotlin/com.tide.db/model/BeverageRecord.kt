package com.tide.db.model

data class BeverageRecord(
    val id: String,
    val drinkType: DrinkType,
    val name: String,
    val amount: Int,
    val createdAt: Long,
    val updatedAt: Long
)