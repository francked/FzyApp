package com.example.ryan.fzyapp.mvp.model;

import com.example.ryan.fzyapp.bean.AndroidIosBean;
import com.example.ryan.fzyapp.bean.MeiZhiBean;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ryan on 18-10-19.
 */

public interface GankApi {

    //请求妹纸图片 接口地址： http://gank.io/api/data/福利/10/1

    @GET("api/data/福利/{num}/{pager}")
    Observable<MeiZhiBean> loadMeZhiInfo(@Path("num")String num, @Path("pager") String pager);

    //请求Android 类信息 接口地址 http://gank.io/api/data/Android/10/1 / http://gank.io/api/data/iOS/10/1
    @GET("api/data/{category}/{num}/{page}")
    Observable<AndroidIosBean> loadAndroidInfo(@Path("category")String category ,@Path("num") String num , @Path("page") String page);

    //请求拓展资源 类信息 接口地址 http://gank.io/api/data/拓展资源/10/1 / http://gank.io/api/data/iOS/10/1
    @GET("api/data/{category}/{num}/{page}")
    Observable<AndroidIosBean> loadResourcesInfo(@Path("category")String category ,@Path("num") String num , @Path("page") String page);

    //请求前端 类信息 接口地址 http://gank.io/api/data/前端/10/1 / http://gank.io/api/data/iOS/10/1
    @GET("api/data/{category}/{num}/{page}")
    Observable<AndroidIosBean> loadFrontInfo(@Path("category")String category ,@Path("num") String num , @Path("page") String page);

    //请求App 类信息 接口地址 http://gank.io/api/data/App/10/1 / http://gank.io/api/data/iOS/10/1
    @GET("api/data/{category}/{num}/{page}")
    Observable<AndroidIosBean> loadAppInfo(@Path("category")String category ,@Path("num") String num , @Path("page") String page);

    //请瞎推荐 类信息 接口地址 http://gank.io/api/data/App/10/1 / http://gank.io/api/data/iOS/10/1
    @GET("api/data/{category}/{num}/{page}")
    Observable<AndroidIosBean> loadBlindInfo(@Path("category")String category ,@Path("num") String num , @Path("page") String page);

}
