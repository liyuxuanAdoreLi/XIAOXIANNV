package com.example.admin.woailiushuang.image

import android.content.Context
import android.widget.ImageView
import com.example.admin.woailiushuang.Utils.MD5Utils
import java.lang.ref.SoftReference

class BitmapRequest(){

    // url
    private lateinit var url:String

    // 用于settag
    private lateinit var urlMD5:String

    // 上下文
    private lateinit var context: Context

    // 加载占位图
    private var resId = -1

    // 监听
    private lateinit var requestListener:RequestListener

    //  imageView
    private lateinit var imageView:SoftReference<ImageView>


    constructor(context: Context) : this() {
        this.context = context
    }


    fun Url(url:String):BitmapRequest{
        this.url = url
        this.urlMD5 = MD5Utils.getMD5(url)
        return this
    }

    fun getUrl():String{
        return this.url
    }

    fun getUrlMd5():String{
        return  this.urlMD5
    }
    fun setListener(requestListener: RequestListener):BitmapRequest{
        this.requestListener = requestListener
        return this
    }

    fun getListener():RequestListener{
        return this.requestListener;
    }

    fun into(imageView: ImageView){
        imageView.setTag(urlMD5)
        this.imageView = SoftReference(imageView)

        RequestManager.getInstance().addBitmapRequest(this)
    }

    fun getImageView():ImageView{
        return  this.imageView.get()!!
    }

    fun loadingRes(resId:Int):BitmapRequest{
        this.resId = resId
        return this
    }


    fun getRes():Int{
        return this.resId
    }
}