package com.tide.history.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.tide.common.base.BaseViewModel
import com.tide.history.domain.DeleteDrinkIntakeRecordUseCase
import com.tide.history.domain.GetIntakeRecordsByPageUseCase
import com.tide.history.domain.UpdateIntakeRecordUseCase
import com.tide.history.ui.components.StoryModel
import com.tide.history.ui.components.toStoryModel
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val updateIntakeRecordUseCase: UpdateIntakeRecordUseCase,
    private val getIntakeRecordsByPageUseCase: GetIntakeRecordsByPageUseCase,
    private val deleteDrinkIntakeRecordUseCase: DeleteDrinkIntakeRecordUseCase
) : BaseViewModel() {

    private val _storyLog = mutableStateOf<List<StoryModel>>(listOf())
    val storyLog: State<List<StoryModel>> = _storyLog

    init {
        loadHistoryIntake()
    }

    fun deleteIntakeRecord(recordId: String) {
        launch {
            deleteDrinkIntakeRecordUseCase(recordId)
        }
    }

    private fun loadHistoryIntake() {
        launch {
            getIntakeRecordsByPageUseCase(0, 100)
                .collect { intakes ->
                    _storyLog.value = intakes
                        .sortedByDescending { it.createdAt }
                        .map { it.toStoryModel() }
                }
        }
    }

}