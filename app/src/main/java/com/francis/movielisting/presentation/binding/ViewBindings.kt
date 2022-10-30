package com.francis.movielisting.presentation.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("bind_visibility_hide_empty_list")
fun View.bindViewVisibilityHideEmptyList(items: List<Any>?) {
    if (items.isNullOrEmpty()) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
    }
}