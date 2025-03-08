package com.tide.hydration.di

import com.tide.hydration.data.HydrationRepository
import com.tide.hydration.data.HydrationRepositoryImpl
import com.tide.hydration.domain.AddDrinkIntakeRecordUseCase
import com.tide.hydration.domain.DeleteDrinkIntakeRecordUseCase
import com.tide.hydration.domain.GetDrinkIntakeRecordsByDateUseCase
import com.tide.hydration.viewmodel.HydrationViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val hydrationModule = DI.Module("hydrationModule") {
    bind<HydrationRepository>() with singleton {
        HydrationRepositoryImpl(instance())
    }
    bind<AddDrinkIntakeRecordUseCase>() with singleton {
        AddDrinkIntakeRecordUseCase(instance())
    }
    bind<GetDrinkIntakeRecordsByDateUseCase>() with singleton {
        GetDrinkIntakeRecordsByDateUseCase(instance())
    }
    bind<DeleteDrinkIntakeRecordUseCase>() with singleton {
        DeleteDrinkIntakeRecordUseCase(instance())
    }
    bind<HydrationViewModel>() with singleton {
        HydrationViewModel(instance(), instance(), instance())
    }
}

val hydrationDI = DI {
    import(hydrationModule)
    extend(com.tide.di.di)
}