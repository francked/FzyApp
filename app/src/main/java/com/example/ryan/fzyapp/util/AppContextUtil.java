package com.example.ryan.fzyapp.util;

import android.content.Context;

import com.example.ryan.fzyapp.MyApplication;

/**
 * Created by ryan on 18-10-19.
 */

public class AppContextUtil {
    private static Context sContext;

    private AppContextUtil() {

    }

    public static void init(Context context) {
        sContext = context;
    }

    public static Context getInstance() {
        if (sContext == null) {
            sContext = MyApplication.getContext();
//            throw new NullPointerException("the context is null,please init AppContextUtil in Application first.");
        }
        return sContext;
    }
}
