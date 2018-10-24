package com.example.ryan.fzyapp.util;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;

import com.example.ryan.fzyapp.BuildConfig;
import com.example.ryan.fzyapp.MyApplication;
import com.example.ryan.fzyapp.mvp.model.GankApi;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ryan on 18-10-19.
 * Retorfit 管理类
 */

public class RetrofitManager {

    //地址
    public static final String BASE_GANK_URL = "http://gank.io/";

    //短缓存有效期为 1分钟
    public static final int CACHE_STALE_SHORT = 60;

    //长缓存有效期为7天
    public static final int CACHE_STALE_LONG = 60 * 60 * 24 * 7;

    public static final String CACHE_CONTROL_AGE = "Cache-Control: public, max-age=";

    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_LONG;

    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
    public static final String CACHE_CONTROL_NETWORK = "max-age=0";

    private OkHttpClient mOkHttpClient;

    private GankApi gankApi;

    private static final String TAG = "RetrofitManager";

    public static RetrofitManager builder(){
        return new RetrofitManager();
    }

    public GankApi getGankApi(){
        return gankApi;
    }

    private RetrofitManager(){
        initOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_GANK_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gankApi = retrofit.create(GankApi.class);
        
    }

    private void initOkHttpClient() {

        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {

                    // 指定缓存路径,缓存大小100Mb
                    File httpCacheDirectory = new File( Environment.getExternalStorageDirectory().getAbsolutePath(),"OkHttpCache");
                    Cache cache = new Cache(httpCacheDirectory, 1024 * 1024 * 300);

                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(new LoggingInterceptor())
//                            .addNetworkInterceptor(new StethoInterceptor())
                            //连接失败 是否重连
                            .retryOnConnectionFailure(true)

                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    // 云端响应头拦截器，用来配置缓存策略
    private Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkConnected()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtil.isNetworkConnected()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl)
                        .removeHeader("Pragma").build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_LONG)
                        .removeHeader("Pragma").build();
            }
        }
    };

    private class LoggingInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (BuildConfig.DEBUG) {
                Log.i(TAG,String.format("发送请求 %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            }
            return chain.proceed(request);

        }
    }


}
