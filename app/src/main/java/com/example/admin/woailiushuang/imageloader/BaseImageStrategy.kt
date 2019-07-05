package com.example.admin.woailiushuang.imageloader

import android.content.Context

interface BaseImageStrategy {
    fun loadImage(context: Context,imageLoader: ImageLoader)
}
