package com.example.admin.woailiushuang.RecycleViewDemoActivity;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

public abstract class MutiAdapter<T> extends CommonAdapter<T> {


    MultiItemTypeSupport<T> mMultiItemTypeSupport ;
    public MutiAdapter(Context context, List<T> datas,MultiItemTypeSupport<T> multiItemTypeSupport){

        super(context,datas,-1);//todo

        mMultiItemTypeSupport = multiItemTypeSupport;


    }


    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position,mDatas.get(position));
    }


    @Override
    public DIYViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);

        DIYViewHolder holder = DIYViewHolder.get(mContext,layoutId,parent);

        return holder;
    }
}
