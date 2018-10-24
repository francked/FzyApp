package com.example.ryan.fzyapp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.ryan.fzyapp.adapters.MeiZhiRecyclerView;
import com.example.ryan.fzyapp.base.BaseActivity;
import com.example.ryan.fzyapp.mvp.view.MeiZhiView;
import com.example.ryan.fzyapp.bean.MeiZhiBean;
import com.example.ryan.fzyapp.mvp.presenter.meizhi.MeiZhiPresenter;

import java.util.List;

/**
 * Created by ryan on 18-10-20.
 */

public class DemoActivity extends BaseActivity implements MeiZhiView {


    private RecyclerView recyclerView;
    private List<MeiZhiBean.ResultsBean> list;
    private MeiZhiRecyclerView adapter;
    private static final String TAG = "DemoActivity";
    @Override
    protected void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    protected void initData() {
        MeiZhiPresenter meiZhiPresenterImp = new MeiZhiPresenter(this,DemoActivity.this);
        meiZhiPresenterImp.loadMeiZhi("10","1");
    }

    @Override
    public int getLayout() {
        return R.layout.demo_layout;
    }

    @Override
    public void loadDataSuccess(MeiZhiBean tData) {
        list = tData.getResults();
        adapter = new MeiZhiRecyclerView(this, list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        Log.d(TAG, "loadDataSuccess: ");
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadDataError(Throwable throwable) {

    }
}
