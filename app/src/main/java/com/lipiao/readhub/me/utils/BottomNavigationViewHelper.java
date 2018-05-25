package com.lipiao.readhub.me.utils;

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

/**
 * @author lipiao
 * @data 2018/5/24.
 * @description 利用反射，改变 item 中 mShiftingMode 的值
 * 将BottomNavigationView默认样式改为均分布局
 * 问题说明及解决：https://www.jianshu.com/p/e2a8791e80d6
 * 在BottomNavigationView中，默认 item > 3 的选中效果会影响item缩放显示方式，故利用反射去掉。其实就是改变 item中mShiftingMode的值
 */
public class BottomNavigationViewHelper {
    public static void disableShiftMode(BottomNavigationView bottomNavigationView) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true); // 允许访问private变量
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
