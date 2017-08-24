package com.guyulei.emarket.fragment;

import android.view.View;

import com.guyulei.emarket.view.LoadingPage;

/**
 * Created by 12539 on 2017/8/24.
 * 分类
 */

public class CategoryFragment extends BaseFragment {
    @Override
    public View onCreateSuccessView() {
        return null;
    }

    @Override
    public LoadingPage.ResultState onLoadNetData() {
        return LoadingPage.ResultState.EMPTY_STATE;
    }
}
