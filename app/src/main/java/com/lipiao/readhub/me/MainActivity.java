package com.lipiao.readhub.me;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lipiao.readhub.me.base.BaseActivity;

/**
 * @author lipiao
 * @data 2018/5/24.
 * @description 主界面
 */
public class MainActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 加载根Fragment
        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.frame_main, MainFragment.newInstance(), true, true);
        }
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

}
