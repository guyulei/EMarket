package com.guyulei.emarket.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.guyulei.emarket.holder.MyBaseHolder;

import java.util.ArrayList;

/**
 * Created by A on 2017/8/24.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {

    public ArrayList<T> mData;
    public MyBaseAdapter(ArrayList<T> data){
        this.mData = data;
    }
    @Override
    public int getCount() {
        return mData.size();
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        MyBaseHolder baseHolder;
        if (convertView == null) {
            baseHolder = getViewHolde();
        }else {
            baseHolder = (MyBaseHolder) convertView.getTag();
        }
        baseHolder.setData(getItem(position));
        return baseHolder.getRootView();
    }

    public abstract MyBaseHolder<T> getViewHolde();
}
