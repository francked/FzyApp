package com.example.ryan.fzyapp.mvp.presenter.android;

import android.content.Context;

import com.example.ryan.fzyapp.bean.AndroidIosBean;
import com.example.ryan.fzyapp.mvp.model.MeiZhiModelImp;
import com.example.ryan.fzyapp.mvp.presenter.BasePresenterImp;
import com.example.ryan.fzyapp.mvp.view.AndroidView;

/**
 * Created by ryan on 18-10-20.
 */

public class AndroidPresenter extends BasePresenterImp<AndroidView , AndroidIosBean> implements IandroidPresenter {

    private Context context = null;

    private MeiZhiModelImp modelImp = null;

    private AndroidView view;

    public AndroidPresenter(AndroidView view , Context context) {
        super(view);
        this.view = view;
        this.context = context;
        modelImp = new MeiZhiModelImp(this.context);
    }

    @Override
    public void loadAndroid(String category,String num, String pager) {
        modelImp.loadAndroid(category,num,pager,this);
    }

    @Override
    public void unSubscribe() {
        modelImp.onUnSubscribe();
    }

    @Override
    public void onDestroy() {
        view = null;
        System.gc();
    }
}
