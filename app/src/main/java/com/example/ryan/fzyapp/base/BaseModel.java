package com.example.ryan.fzyapp.base;

import com.example.ryan.fzyapp.util.RetrofitManager;

/**
 * Created by ryan on 18-10-19.
 */

public class BaseModel {
    public RetrofitManager retrofitManager;

    public BaseModel(){
        retrofitManager = RetrofitManager.builder();
    }
}
