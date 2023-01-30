package com.likemagic.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.likemagic.shoppinglist.data.ShopListRepositoryImpl
import com.likemagic.shoppinglist.domain.EditShopItemUseCase
import com.likemagic.shoppinglist.domain.GetShopListUseCase
import com.likemagic.shoppinglist.domain.RemoveShopItemUseCase
import com.likemagic.shoppinglist.domain.ShopItem
import kotlinx.coroutines.launch

class MainViewModel(application: Application):AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeShopListUseCase = RemoveShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun removeShopItem(shopItem: ShopItem){
        viewModelScope.launch {
            removeShopListUseCase.removeShopItem(shopItem.ID)
        }

    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        viewModelScope.launch {
            editShopItemUseCase.editShopItem(newItem)
        }
    }

}