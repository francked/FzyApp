package com.example.ryan.fzyapp.mvp.presenter.meizhi;

import android.content.Context;

import com.example.ryan.fzyapp.mvp.presenter.BasePresenterImp;
import com.example.ryan.fzyapp.mvp.view.MeiZhiView;
import com.example.ryan.fzyapp.bean.MeiZhiBean;
import com.example.ryan.fzyapp.mvp.model.MeiZhiModelImp;

/**
 * Created by ryan on 18-10-19.
 */

public class MeiZhiPresenter extends BasePresenterImp<MeiZhiView,MeiZhiBean> implements ImeizhiPresenter {

    private Context context = null;
    private MeiZhiModelImp meiZhiModelImp = null;
    private MeiZhiView view;

    public MeiZhiPresenter(MeiZhiView view , Context context) {
        super(view);

        this.view = view;

        this.context = context;

        this.meiZhiModelImp = new MeiZhiModelImp(context);

    }

    @Override
    public void loadMeiZhi(String num, String pager) {
        meiZhiModelImp.loadMeiZhi(num,pager,this);
    }

    @Override
    public void unSubscribe() {
        meiZhiModelImp.onUnSubscribe();
    }

    @Override
    public void onDestroy() {
        view = null;
        System.gc();
    }
}
