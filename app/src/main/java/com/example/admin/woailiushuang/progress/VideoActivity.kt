package com.example.admin.woailiushuang.progress

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.woailiushuang.R
import kotlinx.android.synthetic.main.video_activity.*

class VideoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_activity)

        initView()
    }

    internal fun initView() {
        val videoView = video_view
        videoView.setMediaController(MediaController(this))
        videoView.setOnCompletionListener { Toast.makeText(this@VideoActivity, "视频已完成", Toast.LENGTH_SHORT).show() }

        val uri:Uri = Uri.parse("http://1255580247.vod2.myqcloud.com/39890e29vodcq1255580247/0d513f845285890787865389193/f0.mp4")
//        videoView.setVideoPath("http://1255580247.vod2.myqcloud.com/39890e29vodcq1255580247/0d513f845285890787865389193/f0.mp4")
//        videoView.setVideoPath("http://172.28.6.100/item/video/Y02_01.mp4")

        videoView.setVideoURI(uri)
        startBtn.setOnClickListener {
            videoView.start()
        }

        stop.setOnClickListener {
            videoView.stopPlayback()
        }

        pause.setOnClickListener {
            videoView.pause()
        }


    }


}
