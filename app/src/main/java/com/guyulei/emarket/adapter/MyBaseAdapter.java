package com.guyulei.emarket.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.guyulei.emarket.holder.MoreHolder;
import com.guyulei.emarket.holder.MyBaseHolder;

import java.util.ArrayList;

/**
 * Created by A on 2017/8/24.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {

    public static final int TYPE_MORE  = 0;
    public static final int TYPE_NOMAL = 1;


    public ArrayList<T> mData;

    public MyBaseAdapter(ArrayList<T> data) {
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size() + 1;
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
                baseHolder = new MoreHolder();
            } else {
                //普通 布局
                baseHolder = getViewHolde();
            }
        } else {
            baseHolder = (MyBaseHolder) convertView.getTag();
        }
        if (getItemViewType(position) == TYPE_MORE) {
            //显示 加载更多布局
        } else {
            baseHolder.setData(getItem(position));
        }
        return baseHolder.getRootView();
    }

    public abstract MyBaseHolder<T> getViewHolde();
}
