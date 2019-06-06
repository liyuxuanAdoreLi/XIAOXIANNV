package com.example.admin.woailiushuang.http

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.admin.woailiushuang.JSND
import com.example.admin.woailiushuang.MainActivity
import com.example.admin.woailiushuang.Question
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

open class HttpUtils (){


    val jiazhao = "https://v.juhe.cn/jztk/query?subject=1&model=c1&key="+appKey+"&testType=rand"
    var okHttpClient:OkHttpClient
    init {
        okHttpClient = OkHttpClient()
    }

    companion object {
        val BASEURL = "http://v.juhe.cn/jztk/query"
        val appKey = "70f0aa1216767635826b5e36e71977de"
        val instnce:HttpUtils by lazy { HttpUtils() }
    }

    fun getImageFromUrl(url:String,callback: (Bitmap) -> Unit){
        var bmp : Bitmap
        val request = Request.Builder().get().url(url).build()
                okHttpClient.newCall(request).enqueue( object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        Log.d(MainActivity::class.java.name,"图片加载失败了"
                                + "！！！！！")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val input = response.body!!.byteStream()
                         bmp = BitmapFactory.decodeStream(input)
                        callback(bmp)
                        input.close()
                    }
                })
    }


    fun getBodyByUrl(url:String,callback: (String) ->Unit){
        val request = Request.Builder().get().url(url).build()
        okHttpClient.newCall(request).enqueue( object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(MainActivity::class.java.name,"图片加载失败了"
                        + "！！！！！")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(MainActivity::class.java.name,"图片加载成功！"
                        + "！！！！！")
                val str = response.body!!.string()
                callback(str)
            }
        })
    }


    fun postResquest(request: Request, callback: (MainActivity.CallBk)){

        okHttpClient.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                callback.onFaliel(e)
            }

            override fun onResponse(call: Call, response: Response) {

                val gson:Gson = Gson()
                val date = response.body!!.string()
                val data = gson.fromJson<JSND>(date,object :TypeToken<JSND>(){}.type)

                val result = data.result

                if (result.isNullOrEmpty()){
                    Log.d(MainActivity::class.java.name,
                            "加载结果为空！！！！！"+result)
                } else{
                    Log.d(MainActivity::class.java.name,
                            "加载成功！！！！！"+result)
                    callback.onSuccess(result)
                }
//                val list = gson.fromJson<List<Question>>(result,object :TypeToken<List<Question>>(){}.type)
            }
        })
    }
}