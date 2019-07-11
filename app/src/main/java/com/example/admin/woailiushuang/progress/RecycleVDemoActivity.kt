package com.example.admin.woailiushuang.progress

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.admin.woailiushuang.R
import com.example.admin.woailiushuang.RecycleViewDemoActivity.RvAdapter
import com.example.admin.woailiushuang.http.HttpUtils
import kotlinx.android.synthetic.main.activity_recycle_demo.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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


    companion object {
        var list = mutableListOf<Question>()
    }

    var adapter: RvAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_demo)
        showRv()
    }

    fun showRv() = runBlocking {
        val job = GlobalScope.launch {
            initList()
        }
        job.join()
        initRcView()
    }

    fun initRcView() {
        adapter = RvAdapter(this, list)
        myRecycleView?.adapter = adapter
        val layoutManager = LinearLayoutManager(this@RecycleVDemoActivity)
        layoutManager.orientation = VERTICAL

        // layoutManager
        myRecycleView?.layoutManager = layoutManager


        // itemDecoration
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_line))
        myRecycleView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        refreshLayout.setOnRefreshListener { refreshLayout ->
            initList()
            adapter?.notifyDataSetChanged()
            refreshLayout.finishRefresh(1500/*,false*/)//传入false表示刷新失败
        }
        refreshLayout.setOnLoadMoreListener {
            refreshLayout.finishLoadMore(1500/*,false*/)//传入false表示刷新失败
        }


        myRecycleView?.itemAnimator = DefaultItemAnimator()


//        myRecycleView.addOnScrollListener(object : OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                when (newState) {
//                    SCROLL_STATE_IDLE -> {
//                        Log.i("RecycleView", "视图停止滑动")
//                        Glide.with(this@RecycleVDemoActivity).resumeRequests()
//                    }
//                    SCROLL_STATE_SETTLING -> {
//                        Log.i("RecycleView", "视图惯性滑动")
//                        Glide.with(this@RecycleVDemoActivity).pauseRequests()
//                    }
//                    SCROLL_STATE_DRAGGING -> {
//                        Log.i("RecycleView", "视图正在滑动")
//                        Glide.with(this@RecycleVDemoActivity).resumeRequests()
//                    }
//                }
//            }
//        });
    }

    fun initList() {
        val formBody = FormBody.Builder().add("key", HttpUtils.appKey)
                .add("subject", "1")
                .add("model", "c1")
                .build()
        val request = Request.Builder().url(HttpUtils.BASEURL).post(formBody).build()

        HttpUtils.instnce.postResquest(request, object : MainActivity.CallBk(){
            override fun onFaliel(e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@RecycleVDemoActivity, "post加载失败" + e.message, Toast.LENGTH_SHORT).show()
                    Log.d("post加载失败", e.message)
                }
            }

            override fun onSuccess(slist: List<Question>?) {
                        Log.i("slist----", slist.toString())
                        list = slist as MutableList<Question>
                        list.subList(0, 19)
                        Log.d("slist--------截取后", list.toString())
//                        adapter?.notifyDataSetChanged()
                    }
        })
    }

}