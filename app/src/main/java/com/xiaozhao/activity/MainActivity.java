package com.xiaozhao.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.message.TIMConversationExt;
import com.tencent.imsdk.ext.message.TIMManagerExt;
import com.tencent.qcloud.presentation.event.MessageEvent;
import com.tencent.qcloud.tlslibrary.service.TlsBusiness;
import com.xiaozhao.R;
import com.xiaozhao.base.BaseActivity;
import com.xiaozhao.base.BaseApplication;
import com.xiaozhao.base.MainTab;
import com.xiaozhao.im.UserInfo;
import com.xiaozhao.im.model.Conversation;
import com.xiaozhao.im.model.FriendshipInfo;
import com.xiaozhao.im.model.NomalConversation;
import com.xiaozhao.utils.TDevice;
import com.xiaozhao.utils.UIHelper;
import com.xiaozhao.widget.BadgeView;
import com.xiaozhao.widget.MyFragmentTabHost;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.InjectView;


public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener {


    @InjectView(R.id.realtabcontent)
    FrameLayout realtabcontent;
    @InjectView(android.R.id.tabhost)
    MyFragmentTabHost mTabHost;
    private BadgeView mBvNotice;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case ivLocate:
//            case tvLocate:
//                break;
//            case ivScanner:
//
//                UIHelper.showCaptureActivity(this);
//
//                break;
//            case ivSearch:
//                UIHelper.showSearchActivity(this);
//                break;

        }
    }

    @Override
    public void initView() {

        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        if (Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }

        initTabs();

        mTabHost.setCurrentTab(0);
        mTabHost.setOnTabChangedListener(this);

        if (requestPermission()) {
            Intent intent = new Intent(this, SplashActivity.class);
            finish();
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(TIMManager.getInstance().getEnv() == 0 ? R.string.env_normal : R.string.env_test), Toast.LENGTH_SHORT).show();
        }
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

//        if (mTabHost.getCurrentTab() == MainTab.SHENGHOU.getIdx()) {
//            toolbar.setVisibility(View.GONE);
//        } else {
//            toolbar.setVisibility(View.VISIBLE);
//        }
    }

    private void initTabs() {

        MainTab[] tabs = MainTab.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab mainTab = tabs[i];
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));
            View indicator = LayoutInflater.from(getApplicationContext()).inflate(R.layout.home_tab, null);
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

            if (mainTab.equals(MainTab.ZHENGWU)) {
                View cn = indicator.findViewById(R.id.tab_mes);
                mBvNotice = new BadgeView(MainActivity.this, cn);
                mBvNotice.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
                mBvNotice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                mBvNotice.setBackgroundResource(R.drawable.red_circle_small);
                mBvNotice.setGravity(Gravity.CENTER);

                if (mBvNotice != null) {
                    if (getTotalUnreadNum() == 0) {
                        mBvNotice.hide(true);
                    } else {
                        mBvNotice.show(true);
                    }
                }
            }

        }
        setTitle(mTabHost.getCurrentTabTag());
    }

    private void setTitle(String str) {

    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag(mTabHost.getCurrentTabTag());
    }

    public void logout() {
        TlsBusiness.logout(UserInfo.getInstance().getId());
        UserInfo.getInstance().setId(null);
        MessageEvent.getInstance().clear();
        FriendshipInfo.getInstance().clear();
        UIHelper.showLoginActivity(this);
        finish();
    }


    /**
     * 设置未读tab显示
     */
    public void setMsgUnread(boolean noUnread) {
//        BaseApplication.showToastShort(noUnread + "");
        if (mBvNotice != null) {
            if (noUnread) {
                mBvNotice.hide(true);
            } else {
                mBvNotice.show(true);
            }
        }
    }


    private boolean requestPermission() {
        if (afterM()) {
            final List<String> permissionsList = new ArrayList<>();
            if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                    (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)) {
                return true;
            }
        }
        return false;
    }

    private boolean afterM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    private long getTotalUnreadNum() {
        List<TIMConversation> list = TIMManagerExt.getInstance().getConversationList();
        List<TIMConversation> result = new ArrayList<>();
        for (TIMConversation conversation : list) {
            if (conversation.getType() == TIMConversationType.System) continue;
            result.add(conversation);
            TIMConversationExt conversationExt = new TIMConversationExt(conversation);
            conversationExt.getMessage(1, null, new TIMValueCallBack<List<TIMMessage>>() {
                @Override
                public void onError(int i, String s) {
                    Log.e(TAG, "get message error" + s);
                }

                @Override
                public void onSuccess(List<TIMMessage> timMessages) {
                    if (timMessages.size() > 0) {
                    }

                }
            });

        }
        long num = 0;
        List<Conversation> conversationList = new LinkedList<>();
        for (TIMConversation item : result) {
            switch (item.getType()) {
                case C2C:
//                case Group:
                    conversationList.add(new NomalConversation(item));

                    break;
            }
        }
        for (Conversation conversation : conversationList) {
            num += conversation.getUnreadNum();
        }
        return num;
    }

}
