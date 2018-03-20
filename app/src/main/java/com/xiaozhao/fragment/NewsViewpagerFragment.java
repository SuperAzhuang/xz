package com.xiaozhao.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.xiaozhao.R;
import com.xiaozhao.adapter.ViewPageFragmentAdapter;
import com.xiaozhao.base.BaseListFragment;
import com.xiaozhao.base.BaseViewPagerFragment;
import com.xiaozhao.bean.Channel;
import com.xiaozhao.dao.ChannelDao;
import com.xiaozhao.event.NewChannelEvent;
import com.xiaozhao.event.SelectChannelEvent;
import com.xiaozhao.inter.NewsChannelInter;
import com.xiaozhao.manager.ChannelManager;
import com.xiaozhao.utils.LogUtils;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;


public class NewsViewpagerFragment extends BaseViewPagerFragment implements NewsChannelInter {

    private List<Channel> mSelectedDatas = new ArrayList<>();
    private List<Channel> mUnSelectedDatas = new ArrayList<>();
    private int selectedIndex;
    private String selectedChannel;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_edit:
                ChannelDialogFragment dialogFragment = ChannelDialogFragment.newInstance(mSelectedDatas, mUnSelectedDatas);
                dialogFragment.show(getChildFragmentManager(), "CHANNEL");
                break;
        }
    }

    @Override
    public void initView(View view) {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initData() {
//        获取频道数据
        ChannelManager.getChannel(this);
    }

    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
//		获取tab
//        String[] title = getResources().getStringArray(R.array.news);
        if (mSelectedDatas != null && mSelectedDatas.size() > 0) {
            for (int i = 0; i < mSelectedDatas.size(); i++) {
                adapter.addTab(mSelectedDatas.get(i).getChannelName(), "recommend", NewsListFragment.class, getBundle(NewsListFragment.CATALOG_RECOMMEND));
//            adapter.addTab(title[1], "all", NewsListFragment.class, getBundle(NewsListFragment.CATALOG_COMPLETE));
//            adapter.addTab(title[2], "my", NewsListFragment.class, getBundle(NewsListFragment.CATALOG_MY_LECTURE));
            }
        }
        mTabLayout.notifyDataSetChanged();
    }
    @Subscriber
    private void updateChannel(NewChannelEvent event) {
        LogUtils.d("updateChannel---");
        if (event == null) return;
        if (event.selectedDatas != null && event.unSelectedDatas != null) {
            mSelectedDatas = event.selectedDatas;
            mUnSelectedDatas = event.unSelectedDatas;
            mTabsAdapter.updateChannel(mSelectedDatas);
            mTabLayout.notifyDataSetChanged();
            ChannelDao.saveChannels(event.allChannels);

            List<String> integers = new ArrayList<>();
            for (Channel channel : mSelectedDatas) {
                integers.add(channel.getChannelName());
            }
            if (TextUtils.isEmpty(event.firstChannelName)) {
                if (!integers.contains(selectedChannel)) {
                    selectedChannel = mSelectedDatas.get(selectedIndex).getChannelName();
                    mViewPager.setCurrentItem(selectedIndex, false);
                } else {
                    setViewpagerPosition(integers, selectedChannel);
                }
            } else {
                setViewpagerPosition(integers, event.firstChannelName);
            }
        }
    }

    @Subscriber
    private void selectChannelEvent(SelectChannelEvent selectChannelEvent) {
        LogUtils.d("selectChannelEvent---");
        if (selectChannelEvent == null) return;
        List<String> integers = new ArrayList<>();
        for (Channel channel : mSelectedDatas) {
            integers.add(channel.getChannelName());
        }
        setViewpagerPosition(integers, selectChannelEvent.channelName);
    }
    /**
     * 设置 当前选中页
     *
     * @param integers
     * @param channelName
     */
    private void setViewpagerPosition(List<String> integers, String channelName) {
        if (TextUtils.isEmpty(channelName) || integers == null) return;
        for (int j = 0; j < integers.size(); j++) {
            if (integers.get(j).equals(channelName)) {
                selectedChannel = integers.get(j);
                selectedIndex = j;
                break;
            }
        }
        mViewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewPager.setCurrentItem(selectedIndex, false);
            }
        }, 100);
    }
    private Bundle getBundle(int newType) {
        Bundle bundle = new Bundle();
        bundle.putInt(BaseListFragment.BUNDLE_KEY_CATALOG, newType);
        return bundle;
    }

    @Override
    protected void setScreenPageLimit() {
        mViewPager.setOffscreenPageLimit(3);
    }

    /**
     * 频道数据回调
     *
     * @param mychannels
     * @param otherChannels
     */
    @Override
    public void loadData(List<Channel> mychannels, List<Channel> otherChannels) {
        if (mychannels != null) {
            mSelectedDatas.clear();
            mSelectedDatas.addAll(mychannels);
            mUnSelectedDatas.clear();
            mUnSelectedDatas.addAll(otherChannels);
//            mChannelPagerAdapter = new ChannelPagerAdapter(getChildFragmentManager(), mychannels);
//            mViewpager.setAdapter(mChannelPagerAdapter);
//            mViewpager.setOffscreenPageLimit(2);
//            mViewpager.setCurrentItem(0, false);
//            SlidingTabLayout.setViewPager(mViewpager);
        }
    }
}
