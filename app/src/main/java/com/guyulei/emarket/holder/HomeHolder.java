package com.guyulei.emarket.holder;

import android.view.View;
import android.widget.TextView;

import com.guyulei.emarket.R;
import com.guyulei.emarket.bean.AppInfo;
import com.guyulei.emarket.utils.UIUtils;

/**
 * Created by A on 2017/8/24.
 */

public class HomeHolder extends MyBaseHolder<AppInfo> {

    private TextView mText;

    @Override
    public View initLayout() {
        View inflat = UIUtils.inflat(R.layout.home_list_item);
        mText = (TextView) inflat.findViewById(R.id.text);
        return inflat;
    }

    @Override
    public void refreshView(AppInfo data) {
        mText.setText(data.getName());
    }
}
