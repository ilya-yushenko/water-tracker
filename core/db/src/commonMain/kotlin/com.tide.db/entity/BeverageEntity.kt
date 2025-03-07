package com.tide.db.entity

import com.benasher44.uuid.uuid4
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class BeverageEntity : RealmObject {
    @PrimaryKey
    var id: String = uuid4().toString()
    var drinkType: String = ""
    var name: String = ""
    var amount: Int = 0
    var createdAt: Long = 0L
    var updatedAt: Long = 0L
}