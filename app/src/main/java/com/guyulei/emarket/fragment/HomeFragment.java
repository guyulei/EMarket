package com.guyulei.emarket.fragment;

import android.view.View;
import android.widget.ListView;

import com.guyulei.emarket.adapter.MyBaseAdapter;
import com.guyulei.emarket.holder.HomeHolder;
import com.guyulei.emarket.holder.MyBaseHolder;
import com.guyulei.emarket.utils.UIUtils;
import com.guyulei.emarket.view.LoadingPage.ResultState;

import java.util.ArrayList;

/**
 * Created by 12539 on 2017/8/24.
 * 首页
 */

public class HomeFragment extends BaseFragment {

    private ArrayList<String> mArrayList;

    //本身 已经是 子线程  网络 请求 数据
    //返回 基类  本页面应该 显示 哪种页面
    //获取 数据 成功后 会刷新ui  回调onCreateSuccessView() 方法
    @Override
    public ResultState onLoadNetData() {
        mArrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mArrayList.add("测试数据：" + i);
        }
        return ResultState.SUCCESS_STATE;
    }

    //本身 运行在 主线程
    @Override
    public View onCreateSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new MyAdapter(mArrayList));
        return listView;
    }

    public class MyAdapter extends MyBaseAdapter<String> {

        public MyAdapter(ArrayList data) {
            super(data);
        }

        @Override
        public MyBaseHolder<String> getViewHolde() {
            return new HomeHolder();
        }
    }
}
