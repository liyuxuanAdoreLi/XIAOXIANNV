package com.example.admin.woailiushuang.RecycleViewDemoActivity

import android.content.Context
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.admin.woailiushuang.Data
import com.example.admin.woailiushuang.Question
import com.example.admin.woailiushuang.R
import com.example.admin.woailiushuang.http.HttpUtils
import com.example.admin.woailiushuang.kolinLauch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @desc
 *
 * @auth ${user}
 * @time 2019/3/13 18:43
 */
class RvAdapter : RecyclerView.Adapter<RvAdapter.MyViewHolder> {



    private var list: MutableList<Question>? = null

    private var context:Context?=null

    constructor(mContext: Context, list: MutableList<Question>) {
        context = mContext
        this.list = list
    }

//    override fun onBindViewHolder(holder: MyViewHolder, position: Int, payloads: MutableList<Any>) {
//        super.onBindViewHolder(holder, position, payloads)
//    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
       return list!!.count()
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.itemA.text = list!![position]!!.item1
        holder.itemB.text = list!![position]!!.item2
        holder.itemC.text = list!![position]!!.item3
        holder.itemD.text = list!![position]!!.item4
        holder.title.text = list!![position]!!.question

        holder.exp.text = list!![position]!!.explains

        if (!list!![position]!!.url.isNullOrBlank()){
            Glide.with(context).load(list!![position]!!.url!!).crossFade().into(holder.img)
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.item_recy_demo,p0,false)
        return MyViewHolder(view)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.itemTitle)
        val itemA = itemView.findViewById<TextView>(R.id.itemA)
        val itemB = itemView.findViewById<TextView>(R.id.itemB)
        val itemC = itemView.findViewById<TextView>(R.id.itemC)
        val itemD = itemView.findViewById<TextView>(R.id.itemD)

        val exp = itemView.findViewById<TextView>(R.id.exp)
        val img = itemView.findViewById<ImageView>(R.id.img)



    }


}

