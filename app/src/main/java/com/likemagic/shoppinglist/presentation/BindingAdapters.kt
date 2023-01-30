package com.likemagic.shoppinglist.presentation

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("intToString")
fun intToString(textView: TextView, number: Int){
    textView.text = number.toString()
}
