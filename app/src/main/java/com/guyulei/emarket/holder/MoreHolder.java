package com.guyulei.emarket.holder;

import android.view.View;

import com.guyulei.emarket.R;
import com.guyulei.emarket.utils.UIUtils;

/**
 * Created by A on 2017/8/24.
 */

public class MoreHolder extends MyBaseHolder<Integer> {
    @Override
    public View initLayout() {
        View inflat = UIUtils.inflat(R.layout.more_holder_item);
        return inflat;
    }

    @Override
    public void refreshView(Integer data) {

    }
}
