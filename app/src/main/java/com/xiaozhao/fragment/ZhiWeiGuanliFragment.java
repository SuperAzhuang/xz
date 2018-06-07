package com.xiaozhao.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.xiaozhao.R;
import com.xiaozhao.adapter.ViewPageFragmentAdapter;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.view.EmptyLayout;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/5/25.
 */

public class ZhiWeiGuanliFragment extends BaseFragment {

    @InjectView(R.id.SlidingTabLayout)
    SlidingTabLayout mTabLayout;

    @InjectView(R.id.pager)
    ViewPager mViewPager;
    @InjectView(R.id.error_layout)
    EmptyLayout errorLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"招聘中", "未发布"};
    private ViewPageFragmentAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_guanzhu, null);
        //得到数据
        ButterKnife.inject(this, view);
        return view;
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView(View view) {


        mAdapter = new ViewPageFragmentAdapter(getChildFragmentManager(), mTabLayout, mViewPager);
        mAdapter.addTab(mTitles[0], "info",
                ZhaopingZhongFragment.class, null);
        mAdapter.addTab(mTitles[1], "infojob",
                WeiFabuFragment.class, null);
        mTabLayout.notifyDataSetChanged();
    }

    @Override
    public void initData() {

    }
}
