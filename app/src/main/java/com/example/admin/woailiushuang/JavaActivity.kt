package com.example.admin.woailiushuang

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView

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

    internal var list: MutableList<Data> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_demo)

        val recyclerView = findViewById<RecyclerView>(R.id.myRecycleView)
        val refreshLayout = findViewById<SmartRefreshLayout>(R.id.refreshLayout)
        initData()
        val adapter = RvAdapter(this, list)


        recyclerView.adapter = adapter

        refreshLayout.setOnRefreshListener { refreshLayout ->
            refreshLayout.finishRefresh(2000/*,false*/)//传入false表示刷新失败
        }

    }

    internal fun initData() {
        list.add(Data("fjdsojfo", "fdsohf", true))
        list.add(Data("gfds", "gfdsg", false))
        list.add(Data("hh", "hhh", true))
        list.add(Data("hhh", "erre", false))
        list.add(Data("gfds", "gfdsg", false))
        list.add(Data("hh", "hhh", true))

        list.add(Data("hhh", "erre", false))
        list.add(Data("gfds", "gfdsg", false))
    }


}
