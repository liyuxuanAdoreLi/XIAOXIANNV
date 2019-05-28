package com.example.admin.woailiushuang

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.woailiushuang.consts.EventConsts
import com.example.admin.woailiushuang.coroutine.test
import com.example.admin.woailiushuang.manager.EventManager
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
        EventManager.instnce.getEventBus().register(this)
        initView()
//
//        onClickAction()
//        postItem(Item())
//        this.main1()
//        this.main2()
//        this.main3()
//        this.main4()
//        this.main5()
//        this.main6()

//            Log.i("协程---主线程去你妈的，怎么一直是我最后执行", "协程" + System.currentTimeMillis())//-----1



    }



    public fun initView() {
        toB.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        toRecycleView.setOnClickListener {
            startActivity(Intent(this, RecycleVDemoActivity::class.java))
        }
        scroll.setOnClickListener {
                        tv.translationX +=  10.0f

        }
        xiecheng.setOnClickListener {
            startActivity(Intent(this, test::class.java))
        }
    }

    fun getImageFromAssetFile(context: Context, fileName: String): Bitmap {
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

    suspend fun postItem(item: Item) {
        preparePostAsync { token ->
            this.submitPostAsync(token, item) { post ->
                processPost(post)
            }
        }

    }

    fun preparePostAsync(callback: (Token) -> Unit) {
        // 发起请求并立即返回

        Log.i("回调----这时候执行一段东西", "preparePostAsync（）")
        //do smoething

        // 设置稍后调用的回调
        callback(preparePost())
        Log.i("回调----完毕", "preparePostAsync（）")
        //do something
    }

    //耗时操作 可挂起的函数
    fun preparePost(): Token {
        Log.i("回调----", "preparePost（）")
        return Token()
    }

    fun submitPostAsync(token: Token, item: Item, callback: (Post) -> Unit) {
//            val post = submitPost(token,item)
        Log.i("回调----", "submitPostAsync（）")
        callback(submitPost(token, item))//耗时操作

        Log.i("回调----完毕", "submitPostAsync（）")
    }

    fun submitPost(token: Token, item: Item): Post {

        Log.i("回调----", "submitPost（）")

        return Post()
    }

    fun processPost(post: Post) {
        // 推送 post 对象
        Log.i("回调----", "processPost（）")
    }


    class Token {
        constructor() {
            Log.i("回调----", "Token")
        }
    }

    class Post {
        constructor() {
            Log.i("回调----", "Post")
        }
    }

    class Item {
        constructor() {
            Log.i("回调----", "Item")
        }
    }

}
