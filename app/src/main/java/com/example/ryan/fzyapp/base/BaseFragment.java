package com.example.ryan.fzyapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.fzyapp.mvp.presenter.android.AndroidPresenter;

/**
 * Created by ryan on 18-10-23.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    private T presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(getLayoutId(),container,false);


        initView(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = getPresenter();
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
            presenter = null;
            System.gc();
        }
    }

    public abstract T getPresenter();

    public abstract int getLayoutId();

    public abstract void initView(View view);

    public abstract void initData();
}
