package com.example.admin.woailiushuang.imageloader

import android.content.Context
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.stream.StreamModelLoader
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import java.io.IOException
import java.io.InputStream


class GlideImageStrategy : BaseImageStrategy {
    override fun loadImage(context: Context,imageLoader: ImageLoader) {



    }

    //网络加载
    private  fun loadNormal(){

    }
    //缓存加载
   private fun loadCache(){




        /*Glide.with(ctx).using(StreamModelLoader<String> { model, i, i1 ->
            object : DataFetcher<InputStream> {
                @Throws(Exception::class)
                override fun loadData(priority: Priority): InputStream {
                    throw IOException()
                }

                override fun cleanup() {

                }

                override fun getId(): String {
                    return model
                }

                override fun cancel() {

                }
            }
        })*/

//  .load(img.getUrl()).placeholder(img.getPlaceHolder()).diskCacheStrategy(DiskCacheStrategy.ALL).into(img.getImgView())









    }

}