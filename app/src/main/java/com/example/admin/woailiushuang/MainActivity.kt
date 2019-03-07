package com.example.admin.woailiushuang

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
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

        toB.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    public fun getImageFromAssetFile(context: Context, fileName: String): Bitmap {
        val asm = context.resources.assets
        val input = asm.open(fileName)
        val bitmap = BitmapFactory.decodeStream(input)
        input.close()

        return bitmap
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: EventConsts.MessageEvent) {
        val msg = "onEventMainThread收到了消息：" + event.getMessage()
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
