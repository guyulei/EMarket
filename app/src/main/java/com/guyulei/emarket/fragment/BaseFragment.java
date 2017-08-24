package com.guyulei.emarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guyulei.emarket.utils.UIUtils;
import com.guyulei.emarket.view.LoadingPage;

/**
 * Created by 12539 on 2017/8/24.
 */

public abstract class BaseFragment extends Fragment {

    private static final String ACTIVITY_TAG = "guyulei";
    private LoadingPage mLoadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLoadingPage = new LoadingPage(UIUtils.getContext()) {
            @Override
            public View onCreateSuccess() {
                return onCreateSuccessView();
            }

            @Override
            protected ResultState initNetData() {
                return onLoadNetData();
            }
        };
        Log.e(ACTIVITY_TAG, getClass().getSimpleName());
        return mLoadingPage;
    }

    public abstract View onCreateSuccessView();

    public abstract LoadingPage.ResultState onLoadNetData();

    public void loadNetData() {
        if (mLoadingPage != null) {
            mLoadingPage.loadData();
        }
    }
}
