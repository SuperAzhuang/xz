package com.xiaozhao.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.xiaozhao.R;
import com.xiaozhao.adapter.CompanyInfoAdapter;
import com.xiaozhao.base.BaseActivity;
import com.xiaozhao.fragment.CompanyIntroFragment;
import com.xiaozhao.fragment.HotJobFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.xiaozhao.R.id.tab_title;

public class CompanyActivity extends BaseActivity {


    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.SlidingTabLayout)
    SlidingTabLayout mTabLayout;
    @InjectView(R.id.pager)
    ViewPager mPager;
    @InjectView(R.id.activity_company)
    LinearLayout activityCompany;
    @InjectView(R.id.ivBack)
    ImageView ivBack;
    @InjectView(tab_title)
    TabLayout tabTitle;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;

        }
    }

    @Override
    public void initView() {
        ivBack.setOnClickListener(this);

    }

    @Override
    public void initData() {
        CompanyIntroFragment companyIntroFragment = new CompanyIntroFragment();
        HotJobFragment hotJobFragment = new HotJobFragment();
        ArrayList<Fragment> mLists = new ArrayList<>();
        mLists.add(companyIntroFragment);
        mLists.add(hotJobFragment);

        CompanyInfoAdapter adapter = new CompanyInfoAdapter(getSupportFragmentManager(), mLists);
        mPager.setAdapter(adapter);
//        mTabLayout.setViewPager(mPager);
//        mTabLayout.setLayoutMode((TabLayout.MODE_FIXED));

        tabTitle.addTab(tabTitle.newTab().setText("公司详情"));
        tabTitle.addTab(tabTitle.newTab().setText("热门职位"));
        tabTitle.setTabMode(TabLayout.MODE_FIXED);
        tabTitle.setupWithViewPager(mPager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_company;
    }


}
