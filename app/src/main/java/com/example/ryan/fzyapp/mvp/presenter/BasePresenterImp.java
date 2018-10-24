package com.example.ryan.fzyapp.mvp.presenter;

import android.util.Log;

import com.example.ryan.fzyapp.base.BasePresenter;
import com.example.ryan.fzyapp.mvp.model.IBaseRequestCallBack;
import com.example.ryan.fzyapp.mvp.view.IBaseView;

/**
 * Created by ryan on 18-10-19.
 */

public class BasePresenterImp<V extends IBaseView , T> implements IBaseRequestCallBack<T>  {

    private IBaseView iBaseView = null;
    private static final String TAG = "BasePresenterImp";
    public BasePresenterImp(V view) {
        this.iBaseView = view;
    }


    @Override
    public void beforRequest() {
        Log.d(TAG, "beforRequest: ");
    }

    @Override
    public void requestError(Throwable throwable) {
        iBaseView.loadDataError(throwable);
        Log.d(TAG, "requestError: " + throwable.getMessage());
    }

    @Override
    public void requestComplete() {
        Log.d(TAG, "requestComplete: ");
    }

    @Override
    public void requestSuccess(T CallBack) {
        Log.d(TAG, "requestSuccess: ");
        iBaseView.loadDataSuccess(CallBack);
    }



}
