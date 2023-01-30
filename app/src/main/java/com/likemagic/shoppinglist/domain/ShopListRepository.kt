package com.likemagic.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun removeShopItem(shopItemId: Int)

    suspend fun editShopItem(shopItem: ShopItem)

    suspend fun getShopItem(shopItemId: Int):ShopItem

    fun getShopList():LiveData<List<ShopItem>>
}