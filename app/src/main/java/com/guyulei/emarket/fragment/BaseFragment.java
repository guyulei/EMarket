package com.guyulei.emarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guyulei.emarket.utils.UIUtils;
import com.guyulei.emarket.view.LoadingPage;

import java.util.ArrayList;

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
        return mLoadingPage;
    }

    public abstract View onCreateSuccessView();

    public abstract LoadingPage.ResultState onLoadNetData();

    public void loadNetData() {
        if (mLoadingPage != null) {
            mLoadingPage.loadData();
        }
    }

    public LoadingPage.ResultState checkData(Object obj) {
        if (obj != null) {
            if (obj instanceof ArrayList) {
                ArrayList list = (ArrayList) obj;
                if (!list.isEmpty()) {
                    return LoadingPage.ResultState.SUCCESS_STATE;
                } else {
                    return LoadingPage.ResultState.EMPTY_STATE;//暂无数据
                }
            }
        }
        return LoadingPage.ResultState.ERROR_STATE;
    }
}
