package com.tide.hydration.model

import com.tide.db.model.DrinkType
import watertracker.feature.hydration.generated.resources.Res
import watertracker.feature.hydration.generated.resources.ic_quick_coffee
import watertracker.feature.hydration.generated.resources.ic_quick_juice
import watertracker.feature.hydration.generated.resources.ic_quick_milk
import watertracker.feature.hydration.generated.resources.ic_quick_smoothie
import watertracker.feature.hydration.generated.resources.ic_quick_soda
import watertracker.feature.hydration.generated.resources.ic_quick_tea
import watertracker.feature.hydration.generated.resources.ic_quick_water

enum class HydrationDrinkType {
    WATER, JUICE, COFFEE, TEA, SODA, MILK, SMOOTHIE, OTHER
}

fun HydrationDrinkType.fromHydration() = try {
    DrinkType.valueOf(this.name)
} catch (e: Exception) {
    DrinkType.OTHER
}

fun DrinkType.toHydration() = try {
    HydrationDrinkType.valueOf(this.name)
} catch (e: Exception) {
    HydrationDrinkType.OTHER
}

fun HydrationDrinkType.toIconRes() = when (this) {
    HydrationDrinkType.WATER -> Res.drawable.ic_quick_water
    HydrationDrinkType.JUICE -> Res.drawable.ic_quick_juice
    HydrationDrinkType.COFFEE -> Res.drawable.ic_quick_coffee
    HydrationDrinkType.TEA -> Res.drawable.ic_quick_tea
    HydrationDrinkType.SODA -> Res.drawable.ic_quick_soda
    HydrationDrinkType.MILK -> Res.drawable.ic_quick_milk
    HydrationDrinkType.SMOOTHIE -> Res.drawable.ic_quick_smoothie
    HydrationDrinkType.OTHER -> Res.drawable.ic_quick_water
}