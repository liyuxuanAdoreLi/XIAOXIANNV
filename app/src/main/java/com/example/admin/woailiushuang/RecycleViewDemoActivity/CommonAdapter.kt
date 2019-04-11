package com.example.admin.woailiushuang.RecycleViewDemoActivity

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * @desc
 *我们的每次使用过程中，针对的数据类型Bean肯定是不同的，那么这里肯定要引入泛型代表我们的Bean，内部通过一个List代表我们的数据，
 * @auth ${user}
 * @time 2019/3/15 14:09
 */
//class CommonAdapter<T>: RecyclerView.Adapter<CommonHolder> {
//    var mContext:Context?=null
//    var mData:List<T>?=null
//    var mLayoutId:Int?=null
//    var mInflater:LayoutInflater?=null
//    constructor(context: Context,layoutId:Int,data: List<T> ){
//        mLayoutId = layoutId
//        mData =data
//        mContext = context
//        mInflater = LayoutInflater.from(context)
//    }
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommonHolder {
//
//    }
//
//    override fun getItemCount(): Int {
//    }
//
//    override fun onBindViewHolder(p0: CommonHolder, p1: Int) {
//    }
//
//}