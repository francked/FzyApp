package com.example.ryan.fzyapp.mvp.presenter.android;

import com.example.ryan.fzyapp.base.BasePresenter;

/**
 * Created by ryan on 18-10-20.
 */

public interface IandroidPresenter extends BasePresenter {
    void loadAndroid(String category ,String num, String pager);

    void unSubscribe();
}
