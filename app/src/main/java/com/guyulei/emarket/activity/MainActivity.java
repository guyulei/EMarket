package com.guyulei.emarket.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.guyulei.emarket.R;
import com.guyulei.emarket.base.BaseActivity;
import com.guyulei.emarket.fragment.BaseFragment;
import com.guyulei.emarket.fragment.FragmentFactory;
import com.guyulei.emarket.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewpage)
    ViewPager mViewpage;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());

        mTabLayout.setupWithViewPager(mViewpage);
        mViewpage.setAdapter(myAdapter);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //获取 当前页 fragment
                BaseFragment fragment = FragmentFactory.createFragment(tab.getPosition());
                fragment.loadNetData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    class MyAdapter extends FragmentPagerAdapter {

        private final String[] mStrings;

        public MyAdapter(FragmentManager fm) {
            super(fm);
            mStrings = UIUtils.getStrings(R.array.tab_names);
        }

        @Override
        public Fragment getItem(int position) {
            BaseFragment fragment = FragmentFactory.createFragment(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return mStrings.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mStrings[position];
        }
    }
}
