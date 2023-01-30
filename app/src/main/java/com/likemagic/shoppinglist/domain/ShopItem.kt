package com.likemagic.shoppinglist.domain

data class ShopItem(
    val name: String,
    val count: Int,
    var enabled:Boolean,
    var ID: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
