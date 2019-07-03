package com.example.admin.woailiushuang.image

import android.content.Context
import android.widget.ImageView
import java.util.*

class NewGlide() {
    fun with(context:Context):BitmapRequest{
        return BitmapRequest(context)
    }
}