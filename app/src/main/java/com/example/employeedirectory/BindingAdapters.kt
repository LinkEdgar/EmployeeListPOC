package com.example.employeedirectory

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

@BindingAdapter("setSrc")
fun loadImage(view: ImageView, imageUrl: String) {
    val progressbar = CircularProgressDrawable(view.context).apply {
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }
    Glide.with(view).load(imageUrl).placeholder(progressbar).error(R.mipmap.ic_launcher_round).circleCrop().into(view)
}