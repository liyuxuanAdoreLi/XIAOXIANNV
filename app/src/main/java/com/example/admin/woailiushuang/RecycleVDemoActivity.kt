package com.example.admin.woailiushuang

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.admin.woailiushuang.RecycleViewDemoActivity.RvAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_recycle_demo.*

/**
 * @desc
 *
 * @auth ${user}
 * @time 2019/3/13 18:30
 */
class RecycleVDemoActivity : AppCompatActivity() {

    var list = mutableListOf<Data>()
    var recyclerView: RecyclerView? = null
    var adapter: RvAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_demo)

        initList()
        initRcView()



    }

    fun initRcView() {
        recyclerView = myRecycleView
        adapter = RvAdapter(this, list)
        recyclerView?.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        // layoutManager
        recyclerView?.layoutManager = layoutManager

//        // itemDecoration
//        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
//        itemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_line))
//        recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        // animation
        recyclerView?.itemAnimator = DefaultItemAnimator()
    }

    fun initList() {


        list.add(Data("fjdsojfo", "fdsohf"))
        list.add(Data("gfds", "gfdsg"))
        list.add(Data("hh", "hhh"))
        list.add(Data("hhh", "erre"))
    }


}