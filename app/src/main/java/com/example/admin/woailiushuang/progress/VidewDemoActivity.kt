package com.example.admin.woailiushuang.progress

import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.woailiushuang.R
import kotlinx.android.synthetic.main.url_video_test_layout.*

class VidewDemoActivity:AppCompatActivity() {

    var mediaController:MediaController?=null
    var mediaPlayer:MediaPlayer?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.url_video_test_layout)

        initView()
    }

    fun initView(){
        mediaController = MediaController(this)


        mediaPlayer = MediaPlayer()

        var aFD : AssetFileDescriptor= assets.openFd("susong.mp3")

        mediaPlayer!!.setDataSource(aFD.fileDescriptor,aFD.startOffset,aFD.length)
        mediaPlayer!!.prepare()

        btn.setOnClickListener {
            mediaPlayer!!.start()
        }

    }


}