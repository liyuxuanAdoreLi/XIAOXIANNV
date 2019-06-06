package com.example.admin.woailiushuang

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.woailiushuang.RecycleViewDemoActivity.RvAdapter
import com.example.admin.woailiushuang.http.HttpUtils
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_recycle_demo.*
import okhttp3.FormBody
import okhttp3.Request
import java.io.IOException


/**
 * @desc
 *
 * @auth ${user}
 * @time 2019/3/13 18:30
 */
class RecycleVDemoActivity : AppCompatActivity() {

    var list = mutableListOf<Question>()
    var adapter: RvAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_demo)
        initRcView()
        initList()

    }


    fun initRcViewbyNET(){
        adapter = RvAdapter(this, list)
        myRecycleView?.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

    }

    fun initRcView() {
        adapter = RvAdapter(this, list)
        myRecycleView?.adapter = adapter
        val layoutManager = LinearLayoutManager(this@RecycleVDemoActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        // layoutManager
        myRecycleView?.layoutManager = layoutManager

        // itemDecoration
//        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
//        itemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_line))
//        recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//         animation



        refreshLayout.setOnRefreshListener { refreshLayout ->
            initList()
//            adapter?.notifyDataSetChanged()
            refreshLayout.finishRefresh(1500/*,false*/)//传入false表示刷新失败
        }
        refreshLayout.setOnLoadMoreListener {
//            list.removeAt(5)
//            list.removeAt(4)
//            adapter?.notifyDataSetChanged()
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

//        myRecycleView?.itemAnimator = DefaultItemAnimator()
    }

    fun initList() {
        val formBody = FormBody.Builder().add("key", HttpUtils.appKey)
                .add("subject","1")
                .add("model","c1")
                .build()
        val request = Request.Builder().url(HttpUtils.BASEURL).post(formBody).build()
        HttpUtils.instnce.postResquest(request,object : MainActivity.CallBk {
            override fun onFaliel(e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@RecycleVDemoActivity,"post加载失败"+e.message, Toast.LENGTH_SHORT).show()
                    Log.d("post加载失败",e.message)
                }
            }
            override fun onSuccess(slist: List<Question>?) {
                Log.i("slist----",slist.toString())
                list = slist as MutableList<Question>
//                runOnUiThread {
                    adapter?.notifyDataSetChanged()
//                }
            }
        })
    }

}