package com.example.admin.woailiushuang

import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.admin.woailiushuang.RecycleViewDemoActivity.RvAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_recycle_demo.*


/**
 * @desc
 *
 * @auth ${user}
 * @time 2019/3/13 18:30
 */
class RecycleVDemoActivity : AppCompatActivity() {

    var list = mutableListOf<Data>()
    var adapter: RvAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_demo)

        initList()
        initRcView()
    }

    fun initRcView() {
        adapter = RvAdapter(this, list)
        myRecycleView?.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        // layoutManager
        myRecycleView?.layoutManager = layoutManager

        // itemDecoration
//        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
//        itemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_line))
//        recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//         animation


        myRecycleView.adapter = adapter

        refreshLayout.setOnRefreshListener { refreshLayout ->
            initList()
            adapter?.notifyDataSetChanged()
            refreshLayout.finishRefresh(1500/*,false*/)//传入false表示刷新失败
        }
        refreshLayout.setOnLoadMoreListener {
            list.removeAt(5)
            list.removeAt(4)
            adapter?.notifyDataSetChanged()
            refreshLayout.finishLoadMore(1500/*,false*/)//传入false表示刷新失败
        }

        /*refreshLayoutsetOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {//设置刷新监听器
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {//模拟耗时操作
                    @Override
                    public void run() {
                        swipe_refresh_layout.setRefreshing(false);//取消刷新

                    }
                },2000);
            }
        });    refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {//设置刷新监听器
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {//模拟耗时操作
                    @Override
                    public void run() {
                        swipe_refresh_layout.setRefreshing(false);//取消刷新

                    }
                },2000);
            }
        });*/
//        refreshLayout.setOnLoadMoreListener { refreshlayout ->
//            refreshlayout.finishLoadMore(2000/*,false*/)//传入false表示加载失败
//        }

//        refreshLayout.setOnRefreshListener {
//
//            initList()
//            adapter?.notifyDataSetChanged()
//            Handler().postDelayed({
//
//                refreshLayout.isRefreshing = false//取消刷新
//            }, 2000)
//        }

        myRecycleView?.itemAnimator = DefaultItemAnimator()
    }

    fun initList() {


        list.add(Data("fjdsojfo", "fdsohf",true))
        list.add(Data("gfds", "gfdsg",false))
        list.add(Data("hh", "hhh",true))
        list.add(Data("hhh", "erre",false))
        list.add(Data("gfds", "gfdsg",false))
        list.add(Data("hh", "hhh",true))

        list.add(Data("hhh", "erre",false))

    }


}