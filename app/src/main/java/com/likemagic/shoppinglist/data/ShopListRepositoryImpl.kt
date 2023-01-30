package com.likemagic.shoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.likemagic.shoppinglist.domain.ShopItem
import com.likemagic.shoppinglist.domain.ShopListRepository

class ShopListRepositoryImpl(
    application: Application
):ShopListRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()


    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun removeShopItem(shopItemId: Int) {
        shopListDao.deleteShopItem(shopItemId)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
       return mapper.mapDbModelToEntity(shopListDao.getShopItem(shopItemId))
    }

    override fun getShopList(): LiveData<List<ShopItem>> = MediatorLiveData<List<ShopItem>>().apply {
        addSource(shopListDao.getShopList()){
            value = mapper.mapListDbModelToListEntity(it)
        }
    }
}