package com.example.ryan.fzyapp.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.ryan.fzyapp.R;
import com.example.ryan.fzyapp.adapters.MeiZhiRecyclerView;
import com.example.ryan.fzyapp.base.BaseFragment;
import com.example.ryan.fzyapp.mvp.view.MeiZhiView;
import com.example.ryan.fzyapp.bean.MeiZhiBean;
import com.example.ryan.fzyapp.mvp.presenter.meizhi.MeiZhiPresenter;

import java.util.List;

/**
 */
public class MeiZhiFragment extends BaseFragment<MeiZhiPresenter> implements MeiZhiView {

    public static MeiZhiFragment instance ;
    private RecyclerView recyclerView;
    private List<MeiZhiBean.ResultsBean> list;
    private MeiZhiRecyclerView adapter;
    private static final String TAG = "MeiZhiFragment";
    private MeiZhiPresenter meiZhiPresenterImp;

    public MeiZhiFragment() {
        // Required empty public constructor
    }


    public static MeiZhiFragment newInstance() {
        if (instance == null) {
            instance = new MeiZhiFragment();
        }
        return instance;
    }

    @Override
    public MeiZhiPresenter getPresenter() {
        meiZhiPresenterImp = new MeiZhiPresenter(this,getActivity());
        return meiZhiPresenterImp;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mei_zhi;
    }

    @Override
    public void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_meiZhi);

    }

    @Override
    public void initData() {
        meiZhiPresenterImp.loadMeiZhi("10","1");
    }

    @Override
    public void loadDataSuccess(MeiZhiBean tData) {
        list = tData.getResults();
        adapter = new MeiZhiRecyclerView(getActivity(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        Log.d(TAG, "loadDataSuccess: ");
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadDataError(Throwable throwable) {
        Log.d(TAG, "loadDataError: ");
    }
}
