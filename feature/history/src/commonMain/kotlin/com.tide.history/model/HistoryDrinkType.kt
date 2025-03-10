package com.tide.history.model

import com.tide.db.model.DrinkType
import watertracker.feature.history.generated.resources.Res
import watertracker.feature.history.generated.resources.ic_quick_coffee
import watertracker.feature.history.generated.resources.ic_quick_juice
import watertracker.feature.history.generated.resources.ic_quick_milk
import watertracker.feature.history.generated.resources.ic_quick_smoothie
import watertracker.feature.history.generated.resources.ic_quick_soda
import watertracker.feature.history.generated.resources.ic_quick_tea
import watertracker.feature.history.generated.resources.ic_quick_water

enum class HistoryDrinkType {
    WATER, JUICE, COFFEE, TEA, SODA, MILK, SMOOTHIE, OTHER
}

fun HistoryDrinkType.fromHistory() = try {
    DrinkType.valueOf(this.name)
} catch (e: Exception) {
    DrinkType.OTHER
}

fun DrinkType.toHistory() = try {
    HistoryDrinkType.valueOf(this.name)
} catch (e: Exception) {
    HistoryDrinkType.OTHER
}

fun HistoryDrinkType.toIconRes() = when (this) {
    HistoryDrinkType.WATER -> Res.drawable.ic_quick_water
    HistoryDrinkType.JUICE -> Res.drawable.ic_quick_juice
    HistoryDrinkType.COFFEE -> Res.drawable.ic_quick_coffee
    HistoryDrinkType.TEA -> Res.drawable.ic_quick_tea
    HistoryDrinkType.SODA -> Res.drawable.ic_quick_soda
    HistoryDrinkType.MILK -> Res.drawable.ic_quick_milk
    HistoryDrinkType.SMOOTHIE -> Res.drawable.ic_quick_smoothie
    HistoryDrinkType.OTHER -> Res.drawable.ic_quick_water
}