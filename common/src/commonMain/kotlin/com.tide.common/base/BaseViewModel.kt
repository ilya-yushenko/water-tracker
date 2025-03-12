package com.tide.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    val job = SupervisorJob()
    private val dispatcher = Dispatchers.Main

    override val coroutineContext: CoroutineContext
        get() = dispatcher + job + errorHandler

    private val errorHandler = CoroutineExceptionHandler { _, error ->
        error.printStackTrace()
    }

}