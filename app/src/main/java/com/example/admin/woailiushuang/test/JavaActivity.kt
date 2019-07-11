package com.example.admin.woailiushuang.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.woailiushuang.R
import com.example.admin.woailiushuang.progress.Question

import java.util.ArrayList

/**
 * @desc
 * @auth ${user}
 * @time 2019/3/19 11:09
 */
class JavaActivity : AppCompatActivity() {

    internal var list: MutableList<Question> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_demo)


    }

}
