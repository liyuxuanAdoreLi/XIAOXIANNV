package com.example.admin.woailiushuang.RecycleViewDemoActivity;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DIYViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private View mContentView;

    private SparseArray<View> mViews; //存储子view控件


    public DIYViewHolder(Context context, View itemView, ViewGroup viewGroup) {
        super(itemView);
        mContext = context;
        mContentView = itemView;
        mViews = new SparseArray<View>();


    }

    public static DIYViewHolder get(Context context,int layoutId,ViewGroup viewGroup){
        View itemView = LayoutInflater.from(context).inflate(layoutId,viewGroup,false);

        return new DIYViewHolder(context,itemView,viewGroup);
    }

    public <T extends View> T getView(int viewID){
        View view = mViews.get(viewID);

        if (view == null){
            view = mContentView.findViewById(viewID);
            mViews.put(viewID,view);
        }

        return (T) view;
    }


    public void setTextView(TextView view,String s){
        if (view != null) view.setText(s);
    }

}
