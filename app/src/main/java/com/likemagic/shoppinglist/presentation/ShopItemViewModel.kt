package com.likemagic.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.*
import com.likemagic.shoppinglist.data.ShopListRepositoryImpl
import com.likemagic.shoppinglist.domain.AddShopItemUseCase
import com.likemagic.shoppinglist.domain.EditShopItemUseCase
import com.likemagic.shoppinglist.domain.GetShopItemUseCase
import com.likemagic.shoppinglist.domain.ShopItem
import kotlinx.coroutines.launch

class ShopItemViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem:LiveData<ShopItem>
        get() = _shopItem

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName:LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount:LiveData<Boolean>
        get() = _errorInputCount

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen:LiveData<Unit>
        get() = _shouldCloseScreen

    fun getShopItem(id:Int){
        viewModelScope.launch {
            val item = getShopItemUseCase.getShopItem(id)
            _shopItem.value = item
        }
    }

    fun editShopItem(inputName:String?, inputCount:String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        if(validateInput(name, count)){
            viewModelScope.launch {
                _shopItem.value?.let{
                    val item = it.copy(name = name, count = count)
                    editShopItemUseCase.editShopItem(item)
                    finishWork()
                }
            }
        }
    }

    fun addShopItem(inputName:String?, inputCount:String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        if(validateInput(name, count)){
            val shopItem = ShopItem(name, count, true)
            viewModelScope.launch {
                addShopItemUseCase.addShopItem(shopItem)
                finishWork()
            }
        }
    }

    private fun parseName(name:String?):String{
        return name?.trim() ?: ""
    }

    private fun parseCount(count:String?):Int{
        return try{
            count?.trim()?.toInt() ?: 1
        }catch (e: Exception){
            1
        }
    }

    private fun validateInput(name:String, count: Int):Boolean{
        var result = true
        if(name.isBlank()){
            _errorInputName.value = true
            result = false
        }
        if(count <= 0){
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName(){
        _errorInputName.value =false
    }

    fun resetErrorInputCount(){
        _errorInputCount.value =false
    }

    private fun finishWork(){
        _shouldCloseScreen.postValue(Unit)
    }
}