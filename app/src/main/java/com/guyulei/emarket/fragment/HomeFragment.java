package com.guyulei.emarket.fragment;

import android.view.View;

import com.guyulei.emarket.view.LoadingPage.ResultState;

/**
 * Created by 12539 on 2017/8/24.
 * 首页
 */

public class HomeFragment extends BaseFragment {
    @Override
    public View onCreateSuccessView() {
        return null;
    }

    //本身 已经是 子线程  网络 请求 数据
    @Override
    public ResultState onLoadNetData() {
        return ResultState.SUCCESS_STATE;
    }
}
