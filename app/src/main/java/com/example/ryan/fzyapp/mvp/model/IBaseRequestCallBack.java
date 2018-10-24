package com.example.ryan.fzyapp.mvp.model;

/**
 * Created by ryan on 18-10-19.
 */

public interface IBaseRequestCallBack<T> {

    //请求之前的操作
    void beforRequest();

    //请求异常
    void requestError(Throwable throwable);

    //请求完成
    void requestComplete();


    //请求成功
    void requestSuccess(T CallBack);

}
