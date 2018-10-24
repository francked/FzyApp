package com.example.ryan.fzyapp.mvp.model;

import com.example.ryan.fzyapp.bean.AndroidIosBean;
import com.example.ryan.fzyapp.bean.MeiZhiBean;

/**
 * Created by ryan on 18-10-19.
 */

public interface MeizhiModel {

    //写个 获取 妹纸 的 方法
    void loadMeiZhi(String num, String pager , IBaseRequestCallBack<MeiZhiBean> iBaseRequestCallBack);

    /** * @descriptoin 注销subscribe * @author * @param * @date 2017/2/17 19:02 * @return */
    void onUnSubscribe();

    //写个 Android / ios类信息 的方法
    void loadAndroid(String category ,String num , String page , IBaseRequestCallBack<AndroidIosBean> iBaseRequestCallBack);


}
