package com.likemagic.shoppinglist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_items")
data class ShopItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val ID: Int,
    val name: String,
    val count: Int,
    var enabled:Boolean
)