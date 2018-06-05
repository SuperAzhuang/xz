package com.xiaozhao.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.xiaozhao.R;
import com.xiaozhao.adapter.ViewPageFragmentAdapter;
import com.xiaozhao.base.BaseActivity;
import com.xiaozhao.fragment.CompanyInfoEditFragment;
import com.xiaozhao.fragment.CompanyJobEditFragment;
import com.xiaozhao.view.EmptyLayout;

import java.util.ArrayList;

import butterknife.InjectView;

public class MineGuanzhuAcitity extends BaseActivity {

    @InjectView(R.id.SlidingTabLayout)
    SlidingTabLayout mTabLayout;
    @InjectView(R.id.activity_company_info)
    RelativeLayout activityCompanyInfo;
    @InjectView(R.id.pager)
    ViewPager mViewPager;
    @InjectView(R.id.error_layout)
    EmptyLayout errorLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"我的招聘", "我的求职"};
    private ViewPageFragmentAdapter mAdapter;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSave:

                break;
            case R.id.ivBack:
                finish();
                break;
        }
    }

    @Override
    public void initView() {
        TextView framentTitle = (TextView) findViewById(R.id.framentTitle);
        ImageView ivBack = (ImageView) findViewById(R.id.ivBack);
        TextView tvSave = (TextView) findViewById(R.id.tvSave);
        ivBack.setOnClickListener(this);
        tvSave.setVisibility(View.VISIBLE);
        tvSave.setOnClickListener(this);
        framentTitle.setText("我的企业");

        mAdapter = new ViewPageFragmentAdapter(getSupportFragmentManager(), mTabLayout, mViewPager);
        mAdapter.addTab(mTitles[0], "info",
                CompanyInfoEditFragment.class, null);
        mAdapter.addTab(mTitles[1], "infojob",
                CompanyJobEditFragment.class, null);
        mTabLayout.notifyDataSetChanged();
    }

    //    private Bundle getBundle(int newType, Channel channel) {
//        Bundle bundle = new Bundle();
//        count++;
//        bundle.putInt(BaseListFragment.BUNDLE_KEY_CATALOG, newType);
//        bundle.putSerializable(BaseListFragment.BUNDLE_KEY_MODEL, channel);
//        return bundle;
//    }
    @Override
    public void initData() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_guanzhu_acitity;
    }
}
