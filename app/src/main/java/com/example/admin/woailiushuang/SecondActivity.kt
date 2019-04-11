package com.example.admin.woailiushuang

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.woailiushuang.consts.EventConsts
import com.example.admin.woailiushuang.manager.EventManager
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
        setContentView(R.layout.swipcard_demo)

//        toA.setOnClickListener {
//            EventManager.instnce.post(EventConsts.MessageEvent("first Btn clicked"))
//        }


    }
}