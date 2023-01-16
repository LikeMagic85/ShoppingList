package com.likemagic.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.likemagic.shoppinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this) [MainViewModel::class.java]
        viewModel.shopList.observe(this){ items ->
            Log.d("@@@", items.toString())
            val item = items.find { it.name == "item5" }
            if (item != null) {
                viewModel.removeShopItem(item)
            }
            val change = items.find { it.name == "item9" }
            if(change != null && change.enabled){
                viewModel.editShopItem(change)
            }
        }
    }
}