package com.tide.db

import com.tide.db.entity.BeverageEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class RealmProviderImpl : DatabaseProvider {
    private val realm: Realm by lazy {
        val config = RealmConfiguration.Builder(schema = setOf(BeverageEntity::class))
            .schemaVersion(1)
            .build()
        Realm.open(config)
    }

    override fun getRealmInstance(): Realm = realm
}