package com.guyulei.emarket.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.guyulei.emarket.holder.MoreHolder;
import com.guyulei.emarket.holder.MyBaseHolder;
import com.guyulei.emarket.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by A on 2017/8/24.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {


    public static final int     TYPE_NOMAL    = 0;
    public static final int     TYPE_MORE     = 1;
    public              boolean isLoadingMore = false;

    public ArrayList<T> mData;

    public MyBaseAdapter(ArrayList<T> data) {
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size() + 1;//加载更多布局
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;//下拉加载 2种类型
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {//最后一个 加载更多
            return TYPE_MORE;
        } else {
            return getInnerType();
        }
    }

    private int getInnerType() {//预留 接口 子类可重载
        return TYPE_NOMAL;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        MyBaseHolder baseHolder = null;
        if (convertView == null) {
            if (getItemViewType(position) == TYPE_MORE) {
                //加载更多 布局
                baseHolder = new MoreHolder(hasMore());
            } else {
                //普通 布局
                baseHolder = getViewHolde();//子类实现
            }
        } else {
            baseHolder = (MyBaseHolder) convertView.getTag();
        }
        if (getItemViewType(position) != TYPE_MORE) {
            baseHolder.setData(getItem(position));
        } else {
            //显示 加载更多布局    网络 请求数据
            MoreHolder moreHolder = (MoreHolder) baseHolder;
            //有更多数据 就加载
            if (moreHolder.getData() == MoreHolder.MORE_STATE_MORE) {
                loadMoreNetData(moreHolder);
            }
        }
        return baseHolder.getRootView();
    }

    //默认有 更多 布局  子类 可重载
    public boolean hasMore() {
        return true;
    }

    //加载更多数据
    public void loadMoreNetData(final MoreHolder moreHolder) {
        if (!isLoadingMore) {
            isLoadingMore = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final ArrayList<T> moreData = loadMoreData();

                    UIUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (moreData != null) {
                                //有更多数据    一页10条数据
                                if (moreData.size() < 20) {
                                    moreHolder.setData(MoreHolder.MORE_STATE_NONE);
                                    Toast.makeText(UIUtils.getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                                } else {
                                    moreHolder.setData(MoreHolder.MORE_STATE_MORE);
                                }
                                //处理加载的数据
                                mData.addAll(moreData);
                                //刷新界面
                                MyBaseAdapter.this.notifyDataSetChanged();
                            } else {
                                //失败
                                moreHolder.setData(MoreHolder.MORE_STATE_ERROR);
                            }
                            isLoadingMore = false;
                        }
                    });
                }
            }).start();
        }

    }

    public abstract MyBaseHolder<T> getViewHolde();

    //加载更多数据 子类 实现
    public abstract ArrayList<T> loadMoreData();
}
