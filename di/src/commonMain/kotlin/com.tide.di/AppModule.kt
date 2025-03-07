package com.tide.di

import com.tide.db.DatabaseProvider
import com.tide.db.RealmProviderImpl
import com.tide.db.storage.RealmBeverageIntakeStorage
import com.tide.db.storage.BeverageIntakeStorage
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val appModule = DI.Module("appModule") {
    bind<DatabaseProvider>() with singleton { RealmProviderImpl() }
    bind<BeverageIntakeStorage>() with singleton { RealmBeverageIntakeStorage(instance()) }
}

val di = DI {
    import(appModule)
}
