package com.example.ryan.fzyapp.mvp.view;

/**
 * Created by ryan on 18-10-19.
 */

public interface IBaseView<T> {


    void loadDataSuccess(T tData);

    /** * @descriptoin 请求数据错误
     * * @author dc
     * * @param throwable 异常类型
     * * @date 2017/2/16 11:01 */
    void loadDataError(Throwable throwable);

}
