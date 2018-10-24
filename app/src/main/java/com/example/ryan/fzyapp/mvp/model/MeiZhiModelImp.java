package com.example.ryan.fzyapp.mvp.model;

import android.content.Context;

import com.example.ryan.fzyapp.base.BaseModel;
import com.example.ryan.fzyapp.bean.AndroidIosBean;
import com.example.ryan.fzyapp.bean.MeiZhiBean;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ryan on 18-10-19.
 */

public class MeiZhiModelImp extends BaseModel implements MeizhiModel {

    private Context context = null;
    private GankApi gankApi;
    private CompositeSubscription mCompositeSubscription;


    public MeiZhiModelImp(Context mContext) {
        context = mContext;
        gankApi = retrofitManager.getGankApi();
        mCompositeSubscription = new CompositeSubscription();

    }

    @Override
        public void loadMeiZhi(String num, String pager, final IBaseRequestCallBack iBaseRequestCallBack) {
        //将subscribe添加到subscription，用于注销subscribe
        mCompositeSubscription.add(gankApi.loadMeZhiInfo(num,pager)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MeiZhiBean>() {
                    @Override
                    public void onCompleted() {
                        iBaseRequestCallBack.requestComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iBaseRequestCallBack.requestError(e);
                    }

                    @Override
                    public void onNext(MeiZhiBean meiZhiBean) {
                        iBaseRequestCallBack.requestSuccess(meiZhiBean);
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        //onStart它总是在 subscribe 所发生的线程被调用 ,如果你的subscribe不是主线程，则会出错，则需要指定线程;
                        iBaseRequestCallBack.beforRequest();
                    }
                })

        );
    }

    @Override
    public void onUnSubscribe() {
        //判断状态
        if (mCompositeSubscription.isUnsubscribed()){
            mCompositeSubscription.clear();//注销
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void loadAndroid(String category,String num, String page, final IBaseRequestCallBack<AndroidIosBean> iBaseRequestCallBack) {
        mCompositeSubscription.add(gankApi.loadAndroidInfo(category,num,page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<AndroidIosBean>() {
                    @Override
                    public void onCompleted() {
                        iBaseRequestCallBack.requestComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iBaseRequestCallBack.requestError(e);
                    }

                    @Override
                    public void onNext(AndroidIosBean androidIosBean) {
                        iBaseRequestCallBack.requestSuccess(androidIosBean);
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        iBaseRequestCallBack.beforRequest();

                    }
                })
        );

    }
}
