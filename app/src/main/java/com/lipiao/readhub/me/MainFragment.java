package com.lipiao.readhub.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.View;

import com.lipiao.readhub.me.base.BaseFragment;
import com.lipiao.readhub.me.topic.HotTopicFragment;
import com.lipiao.readhub.me.utils.BottomNavigationViewHelper;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author lipiao
 * @data 2018/5/24.
 * @description 主界面fragment
 */
public class MainFragment extends BaseFragment{
    private BottomNavigationView mBottomNavigationView;
    private SupportFragment[] mFragments = new SupportFragment[5];

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initView(View view) {
        mBottomNavigationView = view.findViewById(R.id.bottom_navigation_view);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);

        if (findFragment(HotTopicFragment.class) == null) {
            mFragments[0] = HotTopicFragment.newInstance();
            mFragments[1] = HotTopicFragment.newInstance();
            mFragments[2] = HotTopicFragment.newInstance();
            mFragments[3] = HotTopicFragment.newInstance();
            mFragments[4] = HotTopicFragment.newInstance();
            loadMultipleRootFragment(R.id.frame_main_content, 0, mFragments[0],
                    mFragments[1], mFragments[2], mFragments[3], mFragments[4]);
        }else {
            mFragments[0] = findFragment(HotTopicFragment.class);
            mFragments[1] = findFragment(HotTopicFragment.class);
            mFragments[2] = findFragment(HotTopicFragment.class);
            mFragments[3] = findFragment(HotTopicFragment.class);
            mFragments[4] = findFragment(HotTopicFragment.class);
        }
    }
    @Override
    protected void initData() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int currentItem;
            switch (item.getItemId()) {
                case R.id.menu_hot_topic:
                    currentItem = 0;
                    break;
                case R.id.menu_technology_trends:
                    currentItem = 1;
                    break;
                case R.id.menu_developer_information:
                    currentItem = 2;
                    break;
                case R.id.menu_blockChain_alert:
                    currentItem = 3;
                    break;
                case R.id.menu_more:
                    currentItem = 4;
                    break;
                default:
                    currentItem = 0;
                    break;
            }
//                if (mBottomNavigationView.getSelectedItemId() == item.getItemId()) {
//                    //当前页面已经为对应页面时，则回到顶部或刷新
//                    Fragment currentFragment = mFragments[currentItem];
//                    if (currentFragment instanceof BaseListFragment) {
//                        ((BaseListFragment) currentFragment).onTabClick();
//                    }
//                    if (currentFragment instanceof MoreFragment) {
//                        ((MoreFragment) currentFragment).onTabClick();
//                    }
//                    return true;
//                }
            showHideFragment(mFragments[currentItem]);
            return true;
        });
    }


}
