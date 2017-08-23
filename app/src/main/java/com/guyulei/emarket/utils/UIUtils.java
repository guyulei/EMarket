package com.guyulei.emarket.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Process;
import android.view.View;

import com.guyulei.emarket.base.EMarkerApp;

/**
 * Created by 12539 on 2017/8/23.
 */

public class UIUtils {

    public static Context getContext() {
        return EMarkerApp.getmContext();
    }

    public static Handler getHandler() {
        return EMarkerApp.getmHandler();
    }

    public static int getMainThreadId() {
        return EMarkerApp.getmMainThreadId();
    }

    //
    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }

    public static String[] getStrings(int id) {
        return getContext().getResources().getStringArray(id);
    }

    //
    public static int getColor(int id) {
        return getContext().getResources().getColor(id);
    }

    public static Drawable getDrawable(int id) {
        return getContext().getResources().getDrawable(id);
    }

    //
    public static int getDimen(int id) {
        return getContext().getResources().getDimensionPixelSize(id);//像素值
    }

    //
    public static int dip2px(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;//获取 设备密度
        return (int) (dip * density + 0.5f);
    }

    public static float px2dip(float px) {
        float density = getContext().getResources().getDisplayMetrics().density;//获取 设备密度
        return px / density;
    }

    //
    public static View inflat(int id) {
        return View.inflate(getContext(), id, null);
    }

    //
    public static boolean isRunOnUiThread() {
        int i = Process.myTid();
        if (i == getMainThreadId()) {
            return true;
        } else {
            return false;
        }
    }

    public static void runOnUiThread(Runnable runnable) {
        if (isRunOnUiThread()) {
            runnable.run();
        } else {
            getHandler().post(runnable);
        }
    }

    public static ColorStateList getColorStateList(int id) {

        return getContext().getResources().getColorStateList(id);
    }
}
