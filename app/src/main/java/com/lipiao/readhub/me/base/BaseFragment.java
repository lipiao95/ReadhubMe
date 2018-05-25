package com.lipiao.readhub.me.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author lipiao
 * @data 2018/5/24.
 * @description fragment基类
 */
public abstract class BaseFragment extends SupportFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        initView(view);
        initData();
        return view;
    }

    protected abstract int getFragmentLayout();

    protected abstract void initView(View view);

    protected abstract void initData();

}
