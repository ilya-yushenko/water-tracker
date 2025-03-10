package com.tide.history.di

import com.tide.history.data.HistoryRepository
import com.tide.history.data.HistoryRepositoryImpl
import com.tide.history.domain.DeleteDrinkIntakeRecordUseCase
import com.tide.history.domain.GetIntakeRecordsByPageUseCase
import com.tide.history.domain.UpdateIntakeRecordUseCase
import com.tide.history.viewmodel.HistoryViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val historyModule = DI.Module("historyModule") {
    bind<HistoryRepository>() with singleton {
        HistoryRepositoryImpl(instance())
    }
    bind<GetIntakeRecordsByPageUseCase>() with singleton {
        GetIntakeRecordsByPageUseCase(instance())
    }
    bind<DeleteDrinkIntakeRecordUseCase>() with singleton {
        DeleteDrinkIntakeRecordUseCase(instance())
    }
    bind<UpdateIntakeRecordUseCase>() with singleton {
        UpdateIntakeRecordUseCase(instance())
    }
    bind<HistoryViewModel>() with singleton {
        HistoryViewModel(instance(), instance(), instance())
    }
}

val historyDI = DI {
    import(historyModule)
    extend(com.tide.di.di)
}