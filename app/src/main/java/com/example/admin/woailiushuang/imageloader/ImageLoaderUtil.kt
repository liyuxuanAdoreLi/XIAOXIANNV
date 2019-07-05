package com.example.admin.woailiushuang.imageloader

import android.content.Context

class ImageLoaderUtil{

    private lateinit var mStrategy:BaseImageStrategy;


    constructor(){
        mStrategy = GlideImageStrategy()
    }

    fun loadImageLoader(context: Context,imageLoader: ImageLoader){
        mStrategy.loadImage(context,imageLoader)


    }


    fun setStrategy(strategy: BaseImageStrategy){
        this.mStrategy = strategy
    }
}
