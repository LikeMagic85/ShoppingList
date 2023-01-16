package com.likemagic.shoppinglist.domain

data class ShopItem(
    val ID: Int,
    val name: String,
    val count: Int,
    val enabled:Boolean
)
