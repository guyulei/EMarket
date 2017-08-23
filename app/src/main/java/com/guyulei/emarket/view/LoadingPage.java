package com.guyulei.emarket.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.guyulei.emarket.R;
import com.guyulei.emarket.utils.UIUtils;

/**
 * Created by 12539 on 2017/8/24.
 */


public class LoadingPage extends FrameLayout {

    public static final int STATE_LOADING_UNDO = 1;//未加载
    public static final int STATE_LOADING_LOADING = 2;//加载中
    public static final int STATE_LOADING_ERROR = 3;//加载失败
    public static final int STATE_LOADING_EMPTY = 4;//数据为空
    public static final int STATE_LOADING_SUCCESS = 5;//加载成功


    public int mCurrentState = STATE_LOADING_UNDO;
    private View mLoadingPageView;

    public LoadingPage(@NonNull Context context) {
        this(context,null,0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview();
    }

    private void initview() {
        if (mLoadingPageView == null) {
            mLoadingPageView = UIUtils.inflat(R.layout.page_loading);
            addView(mLoadingPageView);
        }
    }
}
