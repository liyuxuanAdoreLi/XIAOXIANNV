package com.example.admin.woailiushuang.RecycleViewDemoActivity

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @desc
 *RecyclerView要求必须使用ViewHolder模式，一般我们在使用过程中，都需要去建立一个新的ViewHolder然后作为泛型传入Adapter。那么想要建立通用的Adapter，必须有个通用的ViewHolder。
 *首先我们确定下ViewHolder的主要的作用，实际上是
 *
 * 通过成员变量存储对应的convertView中需要操作的子View，避免每次findViewById，从而提升运行的效率。
 *
 *
 * 因为我们需要在外边调用 获得子控件再去给它设置内容，所以要暴露出子控件getView
 * @auth ${user}
 * @time 2019/3/15 12:00
 */
class CommonHolder(mContent: Context, itemView: View ,parent:ViewGroup) : RecyclerView.ViewHolder(itemView) {
//    那么对于不同的ItemType肯定没有办法确定创建哪些成员变量View，取而代之的只能是个集合来存储了

    var context: Context = mContent
    var mViews: SparseArray<View>? = SparseArray<View>()
    var mpView: View = itemView

    //获取viewHolder
    fun get(context: Context, parent: ViewGroup, layoutId: Int): CommonHolder {

        val itemView = LayoutInflater.from(context).inflate(layoutId, parent, false)
        return CommonHolder(context, itemView,parent)
    }


    //获取控件
    fun <T : View> getSonView(viewId: Int): T {
        var view = mViews?.get(viewId)
        if (view == null) {
            view = mpView.findViewById(viewId)
            mViews?.put(viewId, view)
        }
        return view as T
    }

}