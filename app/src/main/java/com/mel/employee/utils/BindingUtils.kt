package com.mel.employee.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("avatar")
fun loadInage(view :ImageView, url : String){
    Glide.with(view)
        .load(url)
        .into(view)
}