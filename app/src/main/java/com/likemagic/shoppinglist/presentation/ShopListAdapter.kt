package com.likemagic.shoppinglist.presentation


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.likemagic.shoppinglist.R
import com.likemagic.shoppinglist.databinding.ShopListItemDisabledBinding
import com.likemagic.shoppinglist.databinding.ShopListItemEnabledBinding
import com.likemagic.shoppinglist.domain.ShopItem

class ShopListAdapter :
    ListAdapter<ShopItem, ShopListAdapter.ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.shop_list_item_disabled
            VIEW_TYPE_ENABLED -> R.layout.shop_list_item_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val binding = viewHolder.binding
        val shopItem = getItem(position)
        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        when(binding){
            is ShopListItemEnabledBinding -> {
                binding.itemShop = shopItem
            }
            is ShopListItemDisabledBinding -> {
                binding.itemShop = shopItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    inner class ShopItemViewHolder(val binding: ViewDataBinding) : ViewHolder(binding.root)

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 30
    }
}

