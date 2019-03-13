package com.example.admin.woailiushuang.RecycleViewDemoActivity

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.admin.woailiushuang.Data
import com.example.admin.woailiushuang.R

/**
 * @desc
 *
 * @auth ${user}
 * @time 2019/3/13 18:43
 */
class RvAdapter : RecyclerView.Adapter<RvAdapter.MyViewHolder> {
    private var list: MutableList<Data>? = null
    private var context:Context?=null

    constructor(mContext: Context, list: MutableList<Data>) {
        context = mContext
        this.list = list
    }

    override fun getItemCount(): Int = list?.size!!

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.content.text = list!![position]!!.content
        holder.title.text = list!![position]!!.title
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.item_recy_demo,p0,false)
        return MyViewHolder(view)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.itemTitle)
        val content = itemView.findViewById<TextView>(R.id.itemTvContent)

    }
}

