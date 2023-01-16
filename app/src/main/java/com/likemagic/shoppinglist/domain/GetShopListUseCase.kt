package com.likemagic.shoppinglist.domain

class GetShopListUseCase(private val shopItemRepository: ShopItemRepository) {

    fun getShopList():List<ShopItem>{
        return shopItemRepository.getShopList()
    }
}