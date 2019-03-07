package com.example.admin.woailiushuang

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1->{
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        im.setImageBitmap(getImageFromAssetFile(this,"pic1.jpg"))

    }

    public fun getImageFromAssetFile(context: Context,fileName:String):Bitmap{

        val asm = context.resources.assets

        val input = asm.open(fileName)
        var bitmap = BitmapFactory.decodeStream(input)
        input.close()

        return bitmap
    }
}
