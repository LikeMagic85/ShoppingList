package com.likemagic.shoppinglist.domain

class RemoveShopItem(private val shopListRepository: ShopListRepository) {

    fun removeShopItem(shopItem: ShopItem){
        shopListRepository.removeShopItem(shopItem)
    }

}