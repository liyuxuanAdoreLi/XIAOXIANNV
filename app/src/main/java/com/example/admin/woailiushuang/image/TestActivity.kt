package com.example.admin.woailiushuang.image

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.woailiushuang.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.imag_test_activity.*

class TestActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.imag_test_activity)

        initView()
        initImageView()
    }

    private fun initView(){
        addImage.setOnClickListener {
            NewGlide().with(this)
                    .Url("http://timgsa.baidu.com/" +
                            "timg?image&quality=80&size=b9999_10000&sec=1559641157130&di=3b5645543641dd1c81390df2e1c99bd9&imgtype=0&src" +
                            "=http%3A%2F%2Fwww.wfjianmei.com%2Fupload_files%2Ftk19822577.jpg")
                    .loadingRes(R.drawable.ic_launcher_background)
                    .setListener(object :RequestListener{
                        override fun onSuccess(bitmap: Bitmap?) {
                            Log.i(TestActivity::class.java.name.toString(),"加载图片成功")
                            Toast.makeText(this@TestActivity,"加载图片成功",Toast.LENGTH_SHORT).show()
                        }

                        override fun onError() {
                            Log.i(TestActivity::class.java.name.toString(),"加载图片失败")
                            Toast.makeText(this@TestActivity,"加载图片失败",Toast.LENGTH_SHORT).show()

                        }

                    })
                    .into(imageNew)
        }





    }

    private fun initImageView() {
        NewGlide().with(this)
                .Url("http://timgsa.baidu.com/" +
                        "timg?image&quality=80&size=b9999_10000&sec=1559641157130&di=3b5645543641dd1c81390df2e1c99bd9&imgtype=0&src" +
                        "=http%3A%2F%2Fwww.wfjianmei.com%2Fupload_files%2Ftk19822577.jpg")
                .loadingRes(R.drawable.ic_launcher_background)
                .setListener(object :RequestListener{
                    override fun onSuccess(bitmap: Bitmap?) {
                        Log.i(TestActivity::class.java.name.toString(),"加载图片成功")
                        Toast.makeText(this@TestActivity,"加载图片成功",Toast.LENGTH_SHORT).show()
                    }

                    override fun onError() {
                        Log.i(TestActivity::class.java.name.toString(),"加载图片失败")
                        Toast.makeText(this@TestActivity,"加载图片失败",Toast.LENGTH_SHORT).show()

                    }

                })
                .into(imageView)
        NewGlide().with(this)
                .Url("https://p.ssl.qhimg.com/dmfd/400_300_/t0120b2f23b554b8402.jpg")
                .loadingRes(R.drawable.ic_launcher_background)
                .setListener(object :RequestListener{
                    override fun onSuccess(bitmap: Bitmap?) {
                        Log.i(TestActivity::class.java.name.toString(),"加载图片成功")
                        Toast.makeText(this@TestActivity,"加载图片成功",Toast.LENGTH_SHORT).show()
                    }

                    override fun onError() {
                        Log.i(TestActivity::class.java.name.toString(),"加载图片失败")
                        Toast.makeText(this@TestActivity,"加载图片失败",Toast.LENGTH_SHORT).show()

                    }

                })
                .into(imageView2)

        NewGlide().with(this)
                .Url("https://pic1.zhimg.com/v2-3b4fc7e3a1195a081d0259246c38debc_1200x500.jpg")
                .loadingRes(R.drawable.ic_launcher_background)
                .setListener(object :RequestListener{
                    override fun onSuccess(bitmap: Bitmap?) {
                        Log.i(TestActivity::class.java.name.toString(),"加载图片成功")
                    }

                    override fun onError() {
                        Log.i(TestActivity::class.java.name.toString(),"加载图片失败")
                    }

                })
                .into(imageView3)

        NewGlide().with(this)
                .Url("http://timgsa.baidu.com/" +
                        "timg?image&quality=80&size=b9999_10000&sec=1559641157130&di=3b5645543641dd1c81390df2e1c99bd9&imgtype=0&src" +
                        "=http%3A%2F%2Fwww.wfjianmei.com%2Fupload_files%2Ftk19822577.jpg")
                .loadingRes(R.drawable.ic_launcher_background)
                .setListener(object :RequestListener{
                    override fun onSuccess(bitmap: Bitmap?) {
                        Log.i(TestActivity::class.java.name.toString(),"加载图片成功")
                        Toast.makeText(this@TestActivity,"加载图片成功",Toast.LENGTH_SHORT).show()
                    }

                    override fun onError() {
                        Log.i(TestActivity::class.java.name.toString(),"加载图片失败")
                        Toast.makeText(this@TestActivity,"加载图片失败",Toast.LENGTH_SHORT).show()

                    }

                })
                .into(imageView4)

        NewGlide().with(this)
                .Url("http://timgsa.baidu.com/" +
                        "timg?image&quality=80&size=b9999_10000&sec=1559641157130&di=3b5645543641dd1c81390df2e1c99bd9&imgtype=0&src" +
                        "=http%3A%2F%2Fwww.wfjianmei.com%2Fupload_files%2Ftk19822577.jpg")
                .loadingRes(R.drawable.ic_launcher_background)
                .setListener(object :RequestListener{
                    override fun onSuccess(bitmap: Bitmap?) {
                        Log.i(TestActivity::class.java.name.toString(),"加载图片成功")
                        Toast.makeText(this@TestActivity,"加载图片成功",Toast.LENGTH_SHORT).show()
                    }

                    override fun onError() {
                        Log.i(TestActivity::class.java.name.toString(),"加载图片失败")
                        Toast.makeText(this@TestActivity,"加载图片失败",Toast.LENGTH_SHORT).show()

                    }

                })
                .into(imageView5)

        NewGlide().with(this)
                .Url("http://timgsa.baidu.com/" +
                        "timg?image&quality=80&size=b9999_10000&sec=1559641157130&di=3b5645543641dd1c81390df2e1c99bd9&imgtype=0&src" +
                        "=http%3A%2F%2Fwww.wfjianmei.com%2Fupload_files%2Ftk19822577.jpg")
                .loadingRes(R.drawable.ic_launcher_background)
                .setListener(object :RequestListener{
                    override fun onSuccess(bitmap: Bitmap?) {
                        Log.i(TestActivity::class.java.name.toString(),"加载图片成功")
                        Toast.makeText(this@TestActivity,"加载图片成功",Toast.LENGTH_SHORT).show()
                    }

                    override fun onError() {
                        Log.i(TestActivity::class.java.name.toString(),"加载图片失败")
                        Toast.makeText(this@TestActivity,"加载图片失败",Toast.LENGTH_SHORT).show()

                    }

                })
                .into(imageView6)
    }


}