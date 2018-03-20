package com.xiaozhao.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.flyco.tablayout.SlidingTabLayout;
import com.xiaozhao.base.BaseListFragment;
import com.xiaozhao.bean.Channel;
import com.xiaozhao.bean.ViewPageInfo;
import com.xiaozhao.fragment.NewsListFragment;
import com.xiaozhao.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("Recycle")
public class ViewPageFragmentAdapter extends FragmentStatePagerAdapter {

	protected final Context mContext;
    protected PagerSlidingTabStrip mPagerStrip;
    protected SlidingTabLayout mTabLayout;
    protected final ViewPager mViewPager;
    protected final ArrayList<ViewPageInfo> mTabs = new ArrayList<ViewPageInfo>();

    public ViewPageFragmentAdapter(FragmentManager fm,
                                   PagerSlidingTabStrip pageStrip, ViewPager pager) {
        super(fm);
        mContext = pager.getContext();
        mPagerStrip = pageStrip;

        mViewPager = pager;
        mViewPager.setAdapter(this);
        mPagerStrip.setViewPager(mViewPager);

    }
    public ViewPageFragmentAdapter(FragmentManager fm,
                                   SlidingTabLayout pageStrip, ViewPager pager) {
        super(fm);
        mContext = pager.getContext();
        mTabLayout = pageStrip;

        mViewPager = pager;
        mViewPager.setAdapter(this);
        mTabLayout.setViewPager(mViewPager);

    }
    public void addTab(String title, String tag, Class<?> clss, Bundle args) {
        ViewPageInfo viewPageInfo = new ViewPageInfo(title, tag, clss, args);
        addFragment(viewPageInfo);
    }

    public void addAllTab(ArrayList<ViewPageInfo> mTabs) {
        for (ViewPageInfo viewPageInfo : mTabs) {
            addFragment(viewPageInfo);
        }
    }

    protected void addFragment(ViewPageInfo info) {
        if (info == null) {
            return;
        }

        // 加入tab title
//        View v = LayoutInflater.from(mContext).inflate(
//                R.layout.base_viewpage_fragment_tab_item, null, false);
//        TextView title = (TextView) v.findViewById(R.id.tab_title);
//        title.setText(info.title);
//        mPagerStrip.addTab(v);

        mTabs.add(info);
        notifyDataSetChanged();
    }

    public void updateChannel(List<Channel> channels){
//        删除之前的tab重新排列
        mTabs.clear();
        for (int i = 0; i < channels.size(); i++) {
            addTab(channels.get(i).getChannelName(), "recommend", NewsListFragment.class, getBundle(NewsListFragment.CATALOG_RECOMMEND));
        }
    }
    private Bundle getBundle(int newType) {
        Bundle bundle = new Bundle();
        bundle.putInt(BaseListFragment.BUNDLE_KEY_CATALOG, newType);
        return bundle;
    }

    /**
     * 移除第一次
     */
    public void remove() {
        remove(0);
    }

    /**
     * 移除一个tab
     * 
     * @param index
     *            备注：如果index小于0，则从第一个开始删 如果大于tab的数量值则从最后一个开始删除
     */
    public void remove(int index) {
        if (mTabs.isEmpty()) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        if (index >= mTabs.size()) {
            index = mTabs.size() - 1;
        }
        mTabs.remove(index);
        mPagerStrip.removeTab(index, 1);
        notifyDataSetChanged();
    }

    /**
     * 移除所有的tab
     */
    public void removeAll() {
        if (mTabs.isEmpty()) {
            return;
        }
        mPagerStrip.removeAllTab();
        mTabs.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        ViewPageInfo info = mTabs.get(position);
        Fragment instantiate = Fragment.instantiate(mContext, info.clss.getName(), info.args);
        
		return instantiate;
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        Log.d("Debug--", mTabs.get(position).title);
        return mTabs.get(position).title;
    }

}