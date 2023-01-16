package com.likemagic.shoppinglist.domain

class RemoveShopItem(private val shopItemRepository: ShopItemRepository) {

    fun removeShopItem(shopItem: ShopItem){
        shopItemRepository.removeShopItem(shopItem)
    }

}