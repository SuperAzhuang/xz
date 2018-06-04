package com.xiaozhao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.xiaozhao.R;
import com.xiaozhao.base.BaseActivity;
import com.xiaozhao.bean.SimpleBackPage;
import com.xiaozhao.view.EmptyLayout;

import java.lang.ref.WeakReference;

import butterknife.InjectView;

public class SimpleBackActivity extends BaseActivity {


    public final static String BUNDLE_KEY_PAGE = "BUNDLE_KEY_PAGE";
    public final static String BUNDLE_KEY_ARGS = "BUNDLE_KEY_ARGS";
    protected WeakReference<Fragment> mFragment;
    protected int mPageValue = -1;


    @InjectView(R.id.framentTitle)
    TextView framentTitle;
    @InjectView(R.id.container)
    FrameLayout container;
    @InjectView(R.id.activity_root)
    RelativeLayout activityRoot;
    public RelativeLayout rlTitle;
    @InjectView(R.id.ivBack)
    ImageView ivBack;
    @InjectView(R.id.error_layout)
    public EmptyLayout mEmptyLayout;
    public TextView tvSave;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
        }
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        if (mPageValue == -1) {
            mPageValue = getIntent().getIntExtra(BUNDLE_KEY_PAGE, 0);
        }
        initFromIntent(mPageValue, getIntent());
    }

    protected void initFromIntent(int pageValue, Intent data) {

        if (data == null) {
            throw new RuntimeException(
                    "you must provide a page info to display");
        }
        SimpleBackPage page = SimpleBackPage.getPageByValue(pageValue);
        if (page == null) {
            throw new IllegalArgumentException("can not find page by value:"
                    + pageValue);
        }
        try {
            Fragment fragment = (Fragment) page.getClz().newInstance();

            Bundle args = data.getBundleExtra(BUNDLE_KEY_ARGS);
            if (args != null) {
                fragment.setArguments(args);
            }

            FragmentTransaction trans = getSupportFragmentManager()
                    .beginTransaction();
            trans.replace(R.id.container, fragment, TAG);
            trans.commitAllowingStateLoss();

            mFragment = new WeakReference<Fragment>(fragment);
            setActionBarTitle(page.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "generate fragment error. by value:" + pageValue);
        }
    }

    public void setActionBarTitle(int resId) {
        if (resId != 0) {
            framentTitle.setText(resId);
        }
    }

    @Override
    public void initView() {
//        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.swiperefresh_color1).init();

        ivBack.setOnClickListener(this);
        tvSave = (TextView) findViewById(R.id.tvSave);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_simple_back_fragment;
    }
}