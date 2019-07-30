package com.example.admin.woailiushuang.RecycleViewDemoActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.woailiushuang.R;

import java.util.ArrayList;
import java.util.List;

public class TestCommonRecycleViewActivity extends AppCompatActivity {

    private RecyclerView mRecycleVidw;

    private List<String> mDates ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_demo_activity);
        initData();
        initVIew2();
    }

    private void initData(){
        mDates = new ArrayList<String>();

        mDates.add("呵额呵");
        mDates.add("hfosj ");
        mDates.add("霓虹灯撒风");
        mDates.add("你发东嫂");
        mDates.add("fads 风");
        mDates.add("放假哦 i 啊电视剧哦我");
    }

    public void initVIew(){
        mRecycleVidw = findViewById(R.id.myRecycleView);
        mRecycleVidw.setLayoutManager(new LinearLayoutManager(this));

        mRecycleVidw.setAdapter(new CommonAdapter<String>(this,mDates,R.layout.common_item){
            @Override
            public void convert(DIYViewHolder holder, String s) {
                TextView tv = holder.getView(R.id.item_title);
                tv.setText(s);
            }
        });
    }


    public void initVIew2(){
        mRecycleVidw = findViewById(R.id.myRecycleView);
        mRecycleVidw.setLayoutManager(new LinearLayoutManager(this));

        mRecycleVidw.setAdapter(new CommonAdapter<String>(this,mDates,R.layout.common_item){
            @Override
            public void convert(DIYViewHolder holder, String s) {
                TextView tv = holder.getView(R.id.item_title);
                tv.setText(s);
            }
        });

        mRecycleVidw.setAdapter(new MutiAdapter<String>(this, mDates, new MultiItemTypeSupport<String>() {
            @Override
            public int getLayoutId(int itemType) {
                return itemType == 1 ? R.layout.common_item : R.layout.common_item2;
            }

            @Override
            public int getItemViewType(int position, String s) {
                return  position% 2 ==0 ? 1:0;
            }
        }) {
            @Override
            public void convert(DIYViewHolder holder, String s) {
                TextView tv = holder.getView(R.id.item_title);
                tv.setText(s);
            }
        });
    }

}
