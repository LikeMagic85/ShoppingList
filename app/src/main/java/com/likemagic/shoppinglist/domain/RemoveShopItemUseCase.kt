package com.likemagic.shoppinglist.domain

class RemoveShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun removeShopItem(shopItemId: Int){
        shopListRepository.removeShopItem(shopItemId)
    }

}