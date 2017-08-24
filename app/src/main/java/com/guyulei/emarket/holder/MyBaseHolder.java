package com.guyulei.emarket.holder;

import android.view.View;

/**
 * Created by A on 2017/8/24.
 */

public abstract class MyBaseHolder<T> {

    public  T    mData;
    private View mRootView;//item 根布局

    public MyBaseHolder() {
        //子类加载布局  findid
        mRootView = initLayout();//子类实现 加载布局 并 findviewbyid
        //加入tag
        mRootView.setTag(this);
    }

    public abstract View initLayout();

    public void setData(T data) {
        mData = data;
        refreshView(mData);
    }

    public T getData() {
        return mData;
    }

    // 提供获取 跟布局
    public View getRootView() {
        return mRootView;
    }

    //刷新数据
    public abstract void refreshView(T data);
}
