package com.example.ryan.fzyapp.mvp.presenter.meizhi;

import com.example.ryan.fzyapp.base.BasePresenter;

/**
 * Created by ryan on 18-10-19.
 */

public interface ImeizhiPresenter extends BasePresenter {
    void loadMeiZhi(String num, String pager);

    void unSubscribe();
}
