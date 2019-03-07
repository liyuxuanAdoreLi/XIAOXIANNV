package com.example.admin.woailiushuang

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.admin.woailiushuang.consts.EventConsts
import kotlinx.android.synthetic.main.activity_b.*
import org.greenrobot.eventbus.EventBus

@SuppressLint("Registered")
/**
 * @desc
 *
 * @auth ${user}
 * @time 2019/3/7 16:44
 */
class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        toA.setOnClickListener {
            EventBus.getDefault().post(EventConsts.MessageEvent("first Btn clicked"))
        }
    }
}