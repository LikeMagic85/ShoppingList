package com.likemagic.shoppinglist.domain

class AddShopListUseCase(private val shopItemRepository: ShopItemRepository) {

    fun addShopItem(shopItem: ShopItem){
        shopItemRepository.addShopItem(shopItem)
    }

}