package com.example.admin.woailiushuang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

import com.example.admin.woailiushuang.RecycleViewDemoActivity.RvAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener

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
