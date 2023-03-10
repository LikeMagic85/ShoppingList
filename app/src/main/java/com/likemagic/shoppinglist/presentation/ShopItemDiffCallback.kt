package com.likemagic.shoppinglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.likemagic.shoppinglist.domain.ShopItem

class ShopItemDiffCallback: DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.ID == newItem.ID
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}