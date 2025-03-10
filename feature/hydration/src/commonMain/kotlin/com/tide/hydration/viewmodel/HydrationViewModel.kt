package com.tide.hydration.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tide.hydration.domain.AddDrinkIntakeRecordUseCase
import com.tide.hydration.domain.DeleteDrinkIntakeRecordUseCase
import com.tide.hydration.domain.GetDrinkIntakeRecordsByDateUseCase
import com.tide.hydration.model.HydrationDrinkType
import com.tide.hydration.ui.components.DrinkModel
import com.tide.hydration.ui.components.StoryModel
import com.tide.hydration.ui.components.toStoryModel
import com.tide.hydration.utils.getFormattedCurrentDate
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class HydrationViewModel(
    private val addDrinkIntakeRecordUseCase: AddDrinkIntakeRecordUseCase,
    private val getDrinkIntakeRecordsByDateUseCase: GetDrinkIntakeRecordsByDateUseCase,
    private val deleteDrinkIntakeRecordUseCase: DeleteDrinkIntakeRecordUseCase
) : ViewModel() {

    private val _currentData = mutableStateOf("")
    val currentData: State<String> = _currentData

    private val _dailyIntake = mutableStateOf(0)
    val dailyIntake: State<Int> = _dailyIntake

    private val _drinks = mutableStateOf<List<DrinkModel>>(listOf())
    val drinks: State<List<DrinkModel>> = _drinks

    private val _storyLog = mutableStateOf<List<StoryModel>>(listOf())
    val storyLog: State<List<StoryModel>> = _storyLog

    init {
        initCurrentData()
        loadDailyIntake()
        initDrinksList()
    }

    fun addIntakeRecord(drinkType: HydrationDrinkType, name: String, amount: Int) {
        viewModelScope.launch {
            addDrinkIntakeRecordUseCase(drinkType, name, amount)
            loadDailyIntake()
        }
    }

    fun deleteIntakeRecord(recordId: String) {
        viewModelScope.launch {
            deleteDrinkIntakeRecordUseCase(recordId)
            loadDailyIntake()
        }
    }

    private fun loadDailyIntake() {
        viewModelScope.launch {
            val today = Clock.System.now().toEpochMilliseconds()
            val intakes = getDrinkIntakeRecordsByDateUseCase(today)
            _dailyIntake.value = intakes.sumOf { it.amount }
            _storyLog.value = intakes
                .sortedByDescending { it.createdAt }
                .map { it.toStoryModel() }
        }
    }

    private fun initDrinksList() {
        val drinksList = listOf(
            DrinkModel("Water", HydrationDrinkType.WATER, 250),
            DrinkModel("Coffee", HydrationDrinkType.COFFEE, 200),
            DrinkModel("Tea", HydrationDrinkType.TEA, 200),
            DrinkModel("Smoothie", HydrationDrinkType.SMOOTHIE, 200),
            DrinkModel("Juice", HydrationDrinkType.JUICE, 200),
            DrinkModel("Milk", HydrationDrinkType.MILK, 200),
            DrinkModel("Soda", HydrationDrinkType.SODA, 200),
            DrinkModel("Other", HydrationDrinkType.OTHER, 200)
        )
        _drinks.value = drinksList
    }

    private fun initCurrentData() {
        _currentData.value = getFormattedCurrentDate()
    }

}