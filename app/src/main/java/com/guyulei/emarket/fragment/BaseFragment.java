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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LoadingPage loadingPage = new LoadingPage(UIUtils.getContext()) {
            @Override
            public View onCreateSuccess() {
                return onCreateSuccessView();
            }
        };
        Log.e(ACTIVITY_TAG, getClass().getSimpleName());
        return loadingPage;
    }

    public abstract View onCreateSuccessView();
}
