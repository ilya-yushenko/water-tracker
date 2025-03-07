package com.tide.db

import io.realm.kotlin.Realm

interface DatabaseProvider {
    fun getRealmInstance(): Realm
}