package com.likemagic.shoppinglist.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val enabled:Boolean,
    var ID: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
