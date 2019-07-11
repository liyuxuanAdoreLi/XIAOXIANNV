package com.example.admin.woailiushuang.imageloader

import android.content.Context
import android.widget.ImageView

class ImageLoader {
    private lateinit var context: Context
    private lateinit var url: String
    private var resId: Int = -1
    private lateinit var imageView: ImageView

    constructor(builder: Builder) {
        context = builder.context
        this.url = builder.url
        this.context = builder.context
        this.resId = builder.resId
        this.imageView = builder.imageView
    }

    fun getUrl(): String {
        return this.url
    }

    class Builder() {
        lateinit var context: Context
        lateinit var url: String
        lateinit var imageView: ImageView
        var resId: Int = -1

        fun with(context: Context):Builder{
            this.context = context
            return this
        }
        fun Url(url: String): Builder {
            this.url = url
            return this
        }

        fun loadign(resId: Int): Builder {
            this.resId = resId
            return this
        }

        fun into(imageView: ImageView): Builder {
            this.imageView = imageView
            return this
        }

        fun build(): ImageLoader {
            return ImageLoader(this)
        }

    }


}
