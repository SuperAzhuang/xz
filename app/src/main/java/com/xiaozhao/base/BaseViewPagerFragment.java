package com.xiaozhao.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.flyco.tablayout.SlidingTabLayout;
import com.xiaozhao.R;
import com.xiaozhao.adapter.ViewPageFragmentAdapter;
import com.xiaozhao.view.EmptyLayout;
import com.xiaozhao.widget.PagerSlidingTabStrip;

import butterknife.ButterKnife;


/**
 * 带有导航条的基类
 */
public abstract class BaseViewPagerFragment extends BaseFragment {

    protected PagerSlidingTabStrip mTabStrip;
    protected ViewPager mViewPager;
    protected ViewPageFragmentAdapter mTabsAdapter;
    protected EmptyLayout mErrorLayout;
    protected FrameLayout mHeadView;
    protected SlidingTabLayout mTabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_viewpage_fragment, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHeadView = (FrameLayout) view.findViewById(R.id.fl_viewPagerHead);
        if (getHeadView() != null) {
            mHeadView.setVisibility(View.VISIBLE);
            mHeadView.addView(getHeadView());
        }
        mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.pager_tabstrip);
        mTabLayout = (SlidingTabLayout) view.findViewById(R.id.SlidingTabLayout);

        mViewPager = (ViewPager) view.findViewById(R.id.pager);

        mErrorLayout = (EmptyLayout) view.findViewById(R.id.error_layout);
        ImageButton    ivEditButton = (ImageButton) view.findViewById(R.id.iv_edit);
        ivEditButton.setOnClickListener(this);
        mTabsAdapter = getViewPageFragmentAdapter();
        setScreenPageLimit();

        onSetupTabAdapter(mTabsAdapter);

    }


    protected ViewPageFragmentAdapter getViewPageFragmentAdapter() {
        return new ViewPageFragmentAdapter(getChildFragmentManager(), mTabLayout, mViewPager);

    }

    public View getHeadView() {
        return null;
    }

    protected void setScreenPageLimit() {
    }

    protected abstract void onSetupTabAdapter(ViewPageFragmentAdapter adapter);

    public Fragment getCurrentFragment() {
        int currentItem = mViewPager.getCurrentItem();
        Object item = mTabsAdapter.instantiateItem(mViewPager, currentItem);
        return (Fragment) item;
    }

}