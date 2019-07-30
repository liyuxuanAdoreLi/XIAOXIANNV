package com.example.admin.woailiushuang.RecycleViewDemoActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<DIYViewHolder> {
    protected Context mContext;
    protected List<T> mDatas;
    protected int mLayoutId;
    protected LayoutInflater inflater;

    public CommonAdapter(Context context, List<T> mDatas, int mLayoutId) {
        this.mContext = context;
        this.mDatas = mDatas;
        this.mLayoutId = mLayoutId;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public DIYViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

         DIYViewHolder holder = DIYViewHolder.get(mContext,mLayoutId,parent);

        return holder;
    }

    @Override
    public void onBindViewHolder(DIYViewHolder holder, int position) {

        convert(holder,mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public abstract void convert( DIYViewHolder holder, T t);

}
