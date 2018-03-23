package com.xiaozhao.activity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.xiaozhao.R;
import com.xiaozhao.base.BaseActivity;
import com.xiaozhao.base.MainTab;
import com.xiaozhao.utils.TDevice;
import com.xiaozhao.utils.UIHelper;
import com.xiaozhao.widget.MyFragmentTabHost;
import com.xiaozhao.zxing.activity.CaptureActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener {


    @InjectView(R.id.toolbar)
    RelativeLayout toolbar;
    @InjectView(R.id.realtabcontent)
    FrameLayout realtabcontent;
    @InjectView(android.R.id.tabhost)
    MyFragmentTabHost mTabHost;
    @InjectView(R.id.ivLocate)
    ImageView ivLocate;
    @InjectView(R.id.tvLocate)
    TextView tvLocate;
    @InjectView(R.id.ivSearch)
    ImageView ivSearch;
    @InjectView(R.id.ivScanner)
    ImageView ivScanner;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLocate:
            case R.id.tvLocate:

                break;
            case R.id.ivScanner:

                UIHelper.showCaptureActivity(this);

                break;
            case R.id.ivSearch:
                UIHelper.showSearchActivity(this);
                break;

        }
    }

    @Override
    public void initView() {
        ivSearch.setOnClickListener(this);
        ivScanner.setOnClickListener(this);
        ivLocate.setOnClickListener(this);
        tvLocate.setOnClickListener(this);
        ivScanner.setOnClickListener(this);

        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        if (Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }

        initTabs();

        mTabHost.setCurrentTab(0);
        mTabHost.setOnTabChangedListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onTabChanged(String tabId) {
        final int size = mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View v = mTabHost.getTabWidget().getChildAt(i);
            if (i == mTabHost.getCurrentTab()) {
                setTitle(mTabHost.getCurrentTabTag());
                v.setSelected(true);
            } else {
                v.setSelected(false);
            }
        }

        // if (mTabHost.getCurrentTab() == MainTab.FIRSTTAB.getIdx()) {
        // mTvLeft.setVisibility(View.VISIBLE);
        // } else {
        // mTvLeft.setVisibility(View.GONE);
        // }
    }

    private void initTabs() {
        MainTab[] tabs = MainTab.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab mainTab = tabs[i];
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));
            View indicator = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_indicator, null);
            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            Drawable drawable = this.getResources().getDrawable(mainTab.getResIcon());
            // tabIcon.setImageDrawable(drawable);
            title.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            title.setCompoundDrawablePadding((int) TDevice.dpToPixel(3));
            title.setPadding((int) TDevice.dpToPixel(3), (int) TDevice.dpToPixel(3), (int) TDevice.dpToPixel(3), (int) TDevice.dpToPixel(3));
            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(indicator);
            tab.setContent(new TabHost.TabContentFactory() {

                @Override
                public View createTabContent(String tag) {
                    return new View(MainActivity.this);
                }
            });
            mTabHost.addTab(tab, mainTab.getClz(), null);


//            mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);
        }
        setTitle(mTabHost.getCurrentTabTag());
    }

    private void setTitle(String str) {

    }
    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag(mTabHost.getCurrentTabTag());
    }
}
