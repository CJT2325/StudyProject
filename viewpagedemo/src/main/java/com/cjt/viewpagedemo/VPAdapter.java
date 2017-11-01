package com.cjt.viewpagedemo;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * =====================================
 * 作    者: 陈嘉桐
 * 版    本：1.1.4
 * 创建日期：2017/10/31
 * 描    述：
 * =====================================
 */
public class VPAdapter extends PagerAdapter {
    List<View> mViewList;

    public VPAdapter(List<View> mViewList) {
        this.mViewList = mViewList;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }
}
