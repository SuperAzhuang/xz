package com.xiaozhao.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.qcloud.presentation.business.LoginBusiness;
import com.tencent.qcloud.presentation.event.MessageEvent;
import com.tencent.qcloud.presentation.event.RefreshEvent;
import com.tencent.qcloud.tlslibrary.service.TLSService;
import com.tencent.qcloud.ui.NotifyDialog;
import com.xiaozhao.R;
import com.xiaozhao.adapter.SplashAdapter;
import com.xiaozhao.base.BaseActivity;
import com.xiaozhao.base.BaseApplication;
import com.xiaozhao.im.UserInfo;
import com.xiaozhao.im.view.DialogActivity;
import com.xiaozhao.manager.Constant;
import com.xiaozhao.utils.UIHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SplashActivity extends BaseActivity implements TIMCallBack {


    @InjectView(R.id.pager)
    ViewPager mPager;
    @InjectView(R.id.iv_start_img)
    ImageView startImg;
    private ObjectAnimator animator;
    private boolean mLogin;

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
                if (mLogin) {
                    // 跳转的主页
//                    redirectTo();
                } else {
//                  判断用户是否登入IM服务器中
//                    BaseApplication.showToastShort(isUserLogin() + "");
                    if (isUserLogin()) {
                        navToHome();
                    } else {
                        navToLogin();
                    }
//                    UIHelper.showLoginActivity(SplashActivity.this);

                }
//                redirectTo();
//                finish();
            }
        }
    };

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

    /**
     * 是否已有用户登录
     */
    public boolean isUserLogin() {
        return UserInfo.getInstance().getId() != null && (!TLSService.getInstance().needLogin(UserInfo.getInstance().getId()));
    }

    /**
     * 跳转到主界面
     */
    public void navToHome() {
        //登录之前要初始化群和好友关系链缓存
        TIMUserConfig userConfig = new TIMUserConfig();
        userConfig.setUserStatusListener(new TIMUserStatusListener() {
            @Override
            public void onForceOffline() {
                Log.d(TAG, "receive force offline message");
                Intent intent = new Intent(SplashActivity.this, DialogActivity.class);
                startActivity(intent);
            }

            @Override
            public void onUserSigExpired() {
                //票据过期，需要重新登录
                new NotifyDialog().show(getString(R.string.tls_expire), getSupportFragmentManager(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                            logout();
                    }
                });
            }
        })
                .setConnectionListener(new TIMConnListener() {
                    @Override
                    public void onConnected() {
                        Log.i(TAG, "onConnected");
                        Toast.makeText(SplashActivity.this, "onConnected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDisconnected(int code, String desc) {
                        Log.i(TAG, "onDisconnected");
                        Toast.makeText(SplashActivity.this, "onDisconnected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onWifiNeedAuth(String name) {
                        Toast.makeText(SplashActivity.this, "onWifiNeedAuth", Toast.LENGTH_SHORT).show();
                    }
                });

        //设置刷新监听
        RefreshEvent.getInstance().init(userConfig);
//        userConfig = FriendshipEvent.getInstance().init(userConfig);
//        userConfig = GroupEvent.getInstance().init(userConfig);
        userConfig = MessageEvent.getInstance().init(userConfig);
        TIMManager.getInstance().setUserConfig(userConfig);
        LoginBusiness.loginIm(UserInfo.getInstance().getId(), UserInfo.getInstance().getUserSig(), this);

    }

    /**
     * imsdk登录失败后回调
     */
    @Override
    public void onError(int i, String s) {
        Log.e(TAG, "login error : code " + i + " " + s);
        switch (i) {
            case 6208:
                //离线状态下被其他终端踢下线
                NotifyDialog dialog = new NotifyDialog();
                dialog.show(getString(R.string.kick_logout), getSupportFragmentManager(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        navToHome();
                    }
                });
                break;
            case 6200:
                Toast.makeText(this, getString(R.string.login_error_timeout), Toast.LENGTH_SHORT).show();
                navToLogin();
                break;
            default:
                Toast.makeText(this, getString(R.string.login_error), Toast.LENGTH_SHORT).show();
                navToLogin();
                break;
        }
    }

    /**
     * 跳转到登录界面
     */
    public void navToLogin() {
        UIHelper.showLoginActivity(this);
        finish();
    }

    /**
     * imsdk登录成功后回调
     */
    @Override
    public void onSuccess() {

        //初始化程序后台后消息推送
//        PushUtil.getInstance();
        //初始化消息监听
        MessageEvent.getInstance();
        String deviceMan = android.os.Build.MANUFACTURER;
        //注册小米和华为推送
//        if (deviceMan.equals("Xiaomi") && shouldMiInit()){
////            MiPushClient.registerPush(getApplicationContext(), "2882303761517480335", "5411748055335");
//        }else if (deviceMan.equals("HUAWEI")){
//            PushManager.requestToken(this);
//        }


//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//        Log.d(TAG, "refreshed token: " + refreshedToken);
//
//        if(!TextUtils.isEmpty(refreshedToken)) {
//            TIMOfflinePushToken param = new TIMOfflinePushToken(169, refreshedToken);
//            TIMManager.getInstance().setOfflinePushToken(param, null);
//        }
//        MiPushClient.clearNotification(getApplicationContext());
        Log.d(TAG, "imsdk env " + TIMManager.getInstance().getEnv());
        UIHelper.showMainActivity(this);
        finish();
    }
}
