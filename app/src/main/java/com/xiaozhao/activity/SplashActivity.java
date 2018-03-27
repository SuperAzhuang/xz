package com.xiaozhao.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.tencent.TIMCallBack;
import com.tencent.TIMManager;
import com.tencent.TIMUser;
import com.xiaozhao.R;
import com.xiaozhao.adapter.SplashAdapter;
import com.xiaozhao.base.BaseActivity;
import com.xiaozhao.base.BaseApplication;
import com.xiaozhao.im.ConstantValues;
import com.xiaozhao.im.SpUtil;
import com.xiaozhao.manager.Constant;
import com.xiaozhao.utils.UIHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SplashActivity extends BaseActivity {


    @InjectView(R.id.pager)
    ViewPager mPager;
    @InjectView(R.id.iv_start_img)
    ImageView startImg;

    private boolean mIsFistStart;
    private SplashAdapter mAdapter;
    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (mIsFistStart) {
                startImg.setVisibility(View.GONE);
                mPager.setVisibility(View.VISIBLE);
//                sp设置
                BaseApplication.set("isFistInstall", false);
            } else {
                if (mLogin ) {
                    // 跳转的主页
                    redirectTo();
                } else {
//                    UIHelper.showLoginActivity(SplashActivity.this);

//im集成逻辑
                    TIMUser user = new TIMUser();
                    user.setAppIdAt3rd(String.valueOf(ConstantValues.SDK_APP_ID));
                    user.setIdentifier(SpUtil.getString(ConstantValues.LOGIN_IDENTIFIER, ""));
                    user.setAccountType(String.valueOf(ConstantValues.ACCOUNT_TYPE));
                    TIMManager.getInstance().login((int) ConstantValues.SDK_APP_ID,
                            user, SpUtil.getString(ConstantValues.LOGIN_SIG, ""), new TIMCallBack() {
                                @Override
                                public void onError(int i, final String s) {
//                                    mObservable.onNext(s);
//                                    mRx = loginFail;
//                                    登入失败，跳转登入界面
                                    UIHelper.showLoginActivity(SplashActivity.this);
                                }

                                @Override
                                public void onSuccess() {
//                                    ContactModel contactModel = new ContactModel();
//                                    contactModel.getFriendList();
//                                    mObservable.onNext("success");
//                                    mRx = loginSuccess;
//                                    登入成功，跳转主页面
                                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                }
                            });


                }
                finish();
            }
        }
    };
    private ObjectAnimator animator;
    private boolean mLogin;

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView() {
        mIsFistStart = BaseApplication.get("isFistInstall", true);
        if (mIsFistStart) {
            mAdapter = new SplashAdapter(this);
            mPager.setAdapter(mAdapter);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        startImagAnimator();
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        mLogin = BaseApplication.get(Constant.IS_LOGINED, false);
        return R.layout.activity_splash;
    }


    private void startImagAnimator() {
        animator = ObjectAnimator.ofFloat(startImg, "alpha", 0.2f, 1f).setDuration(500);

        animator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {
                if (mIsFistStart) {

                } else {
                    mPager.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                mHandler.sendEmptyMessageDelayed(0, 1500);
            }

            @Override
            public void onAnimationCancel(Animator arg0) {
            }
        });

        animator.start();
    }
    /**
     * 跳转到...
     */
    private void redirectTo() {
        UIHelper.showMainActivity(this);
        this.finish();
    }

    @Override
    protected void onPause() {
        if (animator != null) {
            animator.end();
            animator.cancel();
        }
        super.onPause();
        mHandler.removeMessages(0);
    }

    @Override
    protected void onDestroy() {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
        }
        super.onDestroy();
    }
}
