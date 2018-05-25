package com.lipiao.readhub.me.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author lipiao
 * @data 2018/5/24.
 * @description activity基类
 */
public abstract class BaseActivity extends SupportActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());
    }

    protected abstract int getActivityLayout();

}
