package com.guyulei.emarket.holder;

import android.view.View;
import android.widget.LinearLayout;

import com.guyulei.emarket.R;
import com.guyulei.emarket.utils.UIUtils;

/**
 * Created by A on 2017/8/24.
 */

public class MoreHolder extends MyBaseHolder<Integer> {

    public static final int MORE_STATE_MORE  = 1;//显示 加载更多中
    public static final int MORE_STATE_ERROR = 2;//显示 加载更多失败
    public static final int MORE_STATE_NONE  = 3;//显示 没有更多了 (不需要 加载 更多)
    private LinearLayout mLoad_more;
    private LinearLayout mLoad_error;
    private LinearLayout mLoad_none;

    public MoreHolder(boolean hasmore) {
        if (hasmore) {
            setData(MORE_STATE_MORE);//需要显示 加载更多
        } else {
            setData(MORE_STATE_NONE);//不需要 加载 更多
        }
    }

    @Override
    public View initLayout() {
        View inflat = UIUtils.inflat(R.layout.more_holder_item);
        mLoad_more = inflat.findViewById(R.id.more_load_more);
        mLoad_error = inflat.findViewById(R.id.more_load_error);
        mLoad_none = inflat.findViewById(R.id.more_load_none);
        return inflat;
    }

    @Override
    public void refreshView(Integer data) {
        switch (data) {
            case MORE_STATE_MORE://显示 加载更多
                mLoad_more.setVisibility(View.VISIBLE);
                mLoad_error.setVisibility(View.GONE);
                break;
            case MORE_STATE_NONE://显示 没有更多
                mLoad_more.setVisibility(View.GONE);
                mLoad_error.setVisibility(View.GONE);
                break;
            case MORE_STATE_ERROR://显示 加载错误
                mLoad_more.setVisibility(View.GONE);
                mLoad_error.setVisibility(View.VISIBLE);
                break;
        }

    }
}
