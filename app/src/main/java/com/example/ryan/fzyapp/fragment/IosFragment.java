package com.example.ryan.fzyapp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ryan.fzyapp.R;
import com.example.ryan.fzyapp.adapters.AndroidRecyclerView;
import com.example.ryan.fzyapp.bean.AndroidIosBean;
import com.example.ryan.fzyapp.base.BaseFragment;
import com.example.ryan.fzyapp.mvp.presenter.android.AndroidPresenter;
import com.example.ryan.fzyapp.mvp.view.AndroidView;

import java.util.List;

/**
 * Created by ryan on 18-10-23.
 */

public class IosFragment extends BaseFragment<AndroidPresenter> implements AndroidView{
    public static AndroidFragment androidInstance ;
    private RecyclerView android_recyclerView;
    private static final String TAG = "AndroidFragment";
    private List<AndroidIosBean.ResultsBean> list;
    private AndroidRecyclerView adapter;
    private LinearLayoutManager manager;
    private AndroidPresenter androidPresenter;

    public static AndroidFragment newInstance() {
        if (androidInstance == null) {
            androidInstance = new AndroidFragment();
        }
        return androidInstance;
    }

    @Override
    public AndroidPresenter getPresenter() {
        androidPresenter = new AndroidPresenter(this,getActivity());
        return androidPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.android_layout;
    }

    @Override
    public void initView(View view) {
        android_recyclerView = view.findViewById(R.id.recycler_view_android);

    }

    @Override
    public void initData() {
        androidPresenter.loadAndroid("iOS","10","1");
    }

    @Override
    public void loadDataSuccess(AndroidIosBean tData) {

        list = tData.getResults();
        adapter = new AndroidRecyclerView(getActivity(), list);
        manager = new LinearLayoutManager(getActivity());
        android_recyclerView.setLayoutManager(manager);
        Log.d(TAG, "loadDataSuccess: ");
        android_recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadDataError(Throwable throwable) {
        Toast.makeText(getActivity(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
        Log.d(TAG, "loadDataError: " + throwable.getMessage());
    }
}
