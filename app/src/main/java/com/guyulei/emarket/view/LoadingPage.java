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


public abstract class LoadingPage extends FrameLayout {

    public static final int STATE_LOADING_UNDO    = 1;//未加载
    public static final int STATE_LOADING_LOADING = 2;//加载中
    public static final int STATE_LOADING_ERROR   = 3;//加载失败
    public static final int STATE_LOADING_EMPTY   = 4;//数据为空
    public static final int STATE_LOADING_SUCCESS = 5;//加载成功


    public int mCurrentState = STATE_LOADING_UNDO;
    private View mLoadingPage;
    private View mLoadingError;
    private View mLoadingEmpty;
    private View mLoadingSuccess;

    public LoadingPage(@NonNull Context context) {
        this(context, null, 0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview();
    }

    private void initview() {
        //加载中
        if (mLoadingPage == null) {
            mLoadingPage = UIUtils.inflat(R.layout.page_loading);
            addView(mLoadingPage);
        }
        //加载失败
        if (mLoadingError == null) {
            mLoadingError = UIUtils.inflat(R.layout.page_loading_error);
            addView(mLoadingError);
        }
        //数据为空
        if (mLoadingEmpty == null) {
            mLoadingEmpty = UIUtils.inflat(R.layout.page_loading_empty);
            addView(mLoadingEmpty);
        }
        showRightPage();
    }

    private void showRightPage() {
        mLoadingPage.setVisibility((mCurrentState == STATE_LOADING_UNDO || mCurrentState == STATE_LOADING_LOADING) ? View.VISIBLE : View.GONE);
        mLoadingError.setVisibility((mCurrentState == STATE_LOADING_ERROR) ? View.VISIBLE : View.GONE);
        mLoadingEmpty.setVisibility((mCurrentState == STATE_LOADING_EMPTY) ? View.VISIBLE : View.GONE);

        if (mLoadingSuccess == null && mCurrentState == STATE_LOADING_SUCCESS) {
            mLoadingSuccess = onCreateSuccess();
            if (mLoadingSuccess != null) {
                addView(mLoadingSuccess);
            }
        }

        if (mLoadingSuccess != null) {
            mLoadingSuccess.setVisibility((mCurrentState == STATE_LOADING_SUCCESS) ? View.VISIBLE : View.GONE);
        }
    }

    public abstract View onCreateSuccess();

    //加载数据
    public void loadData() {
        if (mCurrentState != STATE_LOADING_LOADING) {
            mCurrentState = STATE_LOADING_LOADING;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //子线程 网络 请求
                    final ResultState resultState = initNetData();
                    //主线程 更新 ui
                    UIUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (resultState != null) {
                                //根据 网络结果 刷新 ui
                                mCurrentState = resultState.getState();
                                showRightPage();
                            }
                        }
                    });
                }
            }).start();
        }
    }

    //网络 返回结果  并分析
    protected abstract ResultState initNetData();

    public enum ResultState {
        SUCCESS_STATE(STATE_LOADING_SUCCESS), EMPTY_STATE(STATE_LOADING_EMPTY), ERROR_STATE(STATE_LOADING_ERROR);
        private int mState;

        private ResultState(int state) {
            this.mState = state;
        }

        public int getState() {
            return mState;
        }
    }
}
