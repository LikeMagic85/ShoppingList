package com.likemagic.shoppinglist.data

import com.likemagic.shoppinglist.domain.ShopItem

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDbModel(
        shopItem.ID,
        shopItem.name,
        shopItem.count,
        shopItem.enabled
    )

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel) = ShopItem(
        shopItemDbModel.name,
        shopItemDbModel.count,
        shopItemDbModel.enabled,
        shopItemDbModel.ID
    )

    fun mapListDbModelToListEntity(listDbModel:List<ShopItemDbModel>) = listDbModel.map {
        mapDbModelToEntity(it)
    }
}