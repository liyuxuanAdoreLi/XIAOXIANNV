package com.example.admin.woailiushuang.RecycleViewDemoActivity;

public interface MultiItemTypeSupport<T> {
    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);
}
