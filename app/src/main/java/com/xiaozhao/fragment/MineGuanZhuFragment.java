package com.xiaozhao.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

public class MineGuanZhuFragment extends BaseFragment {

    @InjectView(R.id.SlidingTabLayout)
    SlidingTabLayout mTabLayout;

    @InjectView(R.id.pager)
    ViewPager mViewPager;
    @InjectView(R.id.error_layout)
    EmptyLayout errorLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"我的招聘", "我的求职"};
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
                MineZhaopingFragment.class, null);
        mAdapter.addTab(mTitles[1], "infojob",
                MineQiuZhiFragment.class, null);
        mTabLayout.notifyDataSetChanged();
    }

    @Override
    public void initData() {

    }
}
