package com.guyulei.emarket.fragment;

import java.util.HashMap;

/**
 * Created by 12539 on 2017/8/24.
 */

public class FragmentFactory {

    private static HashMap<Integer, BaseFragment> mHashMap = new HashMap<>();

    public static BaseFragment createFragment(int postion) {
        BaseFragment fragment = mHashMap.get(postion);
        if (fragment == null) {
            switch (postion) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new AppFragment();
                    break;
                case 2:
                    fragment = new GameFragment();
                    break;
                case 3:
                    fragment = new TopicFragment();
                    break;
                case 4:
                    fragment = new RecommentFragment();
                    break;
                case 5:
                    fragment = new CategoryFragment();
                    break;
                case 6:
                    fragment = new HotFragment();
                    break;
            }
            mHashMap.put(postion, fragment);
        }
        return fragment;
    }
}
