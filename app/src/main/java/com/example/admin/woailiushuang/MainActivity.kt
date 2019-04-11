package com.example.admin.woailiushuang

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.woailiushuang.consts.EventConsts
import com.example.admin.woailiushuang.manager.EventManager
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EventManager.instnce.getEventBus().register(this)

        onClickAction()
    }

    public fun onClickAction(){
        toB.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        toRecycleView.setOnClickListener {
            startActivity(Intent(this, RecycleVDemoActivity::class.java))
        }
        scroll.setOnClickListener {
//            tv.translationX +=  10.0f

        }
    }

    public fun getImageFromAssetFile(context: Context, fileName: String): Bitmap {
        val asm = context.resources.assets
        val input = asm.open(fileName)
        val bitmap = BitmapFactory.decodeStream(input)
        input.close()

        return bitmap
    }
        //注解作用：标记：将来该方法都将被eventBus收录
//    threadMode 一个枚举：代表着EventBus 通过post发送消息时，接受的线程类型是主线程还是子线程
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThad(event: EventConsts.MessageEvent) {
        val msg = "onEventMainThread收到了消息：" + event.data
        Log.d("EventBus", msg)
        tv.setText(msg)
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventManager.instnce.getEventBus().unregister(this)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}
