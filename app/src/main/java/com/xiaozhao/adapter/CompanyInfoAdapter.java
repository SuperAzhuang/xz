package com.xiaozhao.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/22.
 */

public class CompanyInfoAdapter extends FragmentPagerAdapter {


    private ArrayList<Fragment> mFagments;
    private String[] mTitles = {"公司简介", "热门职位"};

    public CompanyInfoAdapter(FragmentManager fm, ArrayList<Fragment> mFagments) {
        super(fm);
        this.mFagments = mFagments;
    }

    @Override
    public int getCount() {
        return mFagments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFagments.get(position);
    }

}
