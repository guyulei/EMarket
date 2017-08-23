package com.guyulei.emarket.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

/**
 * Created by 12539 on 2017/8/23.
 */

public class EMarkerApp extends Application {

    private static Context mContext;
    private static Handler mHandler;
    private static int     mMainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        mHandler = new Handler();
        mMainThreadId = Process.myTid();
    }

    public static Context getmContext() {
        return mContext;
    }

    public static Handler getmHandler() {
        return mHandler;
    }

    public static int getmMainThreadId() {
        return mMainThreadId;
    }
}
