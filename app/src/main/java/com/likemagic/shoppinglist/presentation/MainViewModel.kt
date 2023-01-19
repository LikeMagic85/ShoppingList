package com.likemagic.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.likemagic.shoppinglist.data.ShopListRepositoryImpl
import com.likemagic.shoppinglist.domain.EditShopItemUseCase
import com.likemagic.shoppinglist.domain.GetShopListUseCase
import com.likemagic.shoppinglist.domain.RemoveShopItemUseCase
import com.likemagic.shoppinglist.domain.ShopItem

class MainViewModel:ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeShopListUseCase = RemoveShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun removeShopItem(shopItem: ShopItem){
        removeShopListUseCase.removeShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

}