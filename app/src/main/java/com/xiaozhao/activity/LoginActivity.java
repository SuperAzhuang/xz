package com.xiaozhao.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.qcloud.presentation.business.InitBusiness;
import com.tencent.qcloud.presentation.business.LoginBusiness;
import com.tencent.qcloud.presentation.event.MessageEvent;
import com.tencent.qcloud.presentation.event.RefreshEvent;
import com.tencent.qcloud.presentation.presenter.SplashPresenter;
import com.tencent.qcloud.tlslibrary.service.TLSService;
import com.tencent.qcloud.tlslibrary.service.TlsBusiness;
import com.tencent.qcloud.ui.NotifyDialog;
import com.xiaozhao.R;
import com.xiaozhao.base.BaseActivity;
import com.xiaozhao.im.UserInfo;
import com.xiaozhao.im.view.DialogActivity;
import com.xiaozhao.utils.UIHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSHelper;
import tencent.tls.platform.TLSPwdLoginListener;
import tencent.tls.platform.TLSUserInfo;

import static com.xiaozhao.R.id.etPhone;


public class LoginActivity extends BaseActivity implements TIMCallBack {


    @InjectView(R.id.et_account)
    EditText etAccount;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.tvForget)
    TextView tvForget;
    @InjectView(R.id.btLogin)
    Button btLogin;
    @InjectView(R.id.tvRegist)
    TextView tvRegist;
    @InjectView(R.id.tvThird)
    TextView tvThird;
    @InjectView(R.id.ll)
    LinearLayout ll;
    private AlertDialog dialog;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private TLSHelper tlsHelper;
    private SplashPresenter presenter;
    private int LOGIN_RESULT_CODE = 100;
    private int GOOGLE_PLAY_RESULT_CODE = 200;
    private final int REQUEST_PHONE_PERMISSIONS = 0;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btLogin:
//                UIHelper.showMainActivity(this);
//                finish();
//                这里暂时去做登入im的操作
                initLogin();
                break;
            case R.id.tvRegist:
                UIHelper.showRegistActivity(this);
                finish();
                break;
            case R.id.tvForget:
//                UIHelper.showRegistActivity(this);
//                finish();
                break;
        }

    }

    private void initLogin() {
//        "86-" + etAccount.getText().toString().trim()
        tlsHelper.TLSPwdLogin(etAccount.getText().toString().trim(), etPassword.getText().toString().getBytes(), new TLSPwdLoginListener() {
                    @Override
                    public void OnPwdLoginSuccess(TLSUserInfo tlsUserInfo) {
/* 登录成功了，在这里可以获取用户票据*/
                        String usersig = tlsHelper.getUserSig(tlsUserInfo.identifier);
                        Toast.makeText(LoginActivity.this, "usersig=" + usersig, Toast.LENGTH_SHORT).show();
//登入成功
                        String id = TLSService.getInstance().getLastUserIdentifier();
                        UserInfo.getInstance().setId(id);
                        UserInfo.getInstance().setUserSig(TLSService.getInstance().getUserSig(id));
                        navToHome();

                    }

                    @Override
                    public void OnPwdLoginReaskImgcodeSuccess(byte[] bytes) {

                    }

                    @Override
                    public void OnPwdLoginNeedImgcode(byte[] bytes, TLSErrInfo tlsErrInfo) {

                    }

                    @Override
                    public void OnPwdLoginFail(TLSErrInfo errInfo) {
                        Toast.makeText(LoginActivity.this, "OnPwdRegFail--" + errInfo.ErrCode + "--" + errInfo.Title + "--" + errInfo.Msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void OnPwdLoginTimeout(TLSErrInfo tlsErrInfo) {
                        Toast.makeText(LoginActivity.this, "OnPwdLoginTimeout=", Toast.LENGTH_SHORT).show();
                    }
                }

        );
    }

    @Override
    public void initView() {

        btLogin.setOnClickListener(this);
        tvRegist.setOnClickListener(this);
        tvForget.setOnClickListener(this);

        int sdkAppid = 1400077940;
        final List<String> permissionsList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.READ_PHONE_STATE);
            if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionsList.size() == 0) {
                init();
            } else {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_PHONE_PERMISSIONS);
            }
        } else {
            init();
        }
        String identifier = "86-15059151260";
        tlsHelper = TLSHelper.getInstance().init(getApplicationContext(), sdkAppid);
        tlsHelper.needLogin(identifier);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    /**
     * 初始化IMSDK
     */
    private void init() {
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        int loglvl = pref.getInt("loglvl", TIMLogLevel.DEBUG.ordinal());
        //初始化IMSDK
        InitBusiness.start(getApplicationContext(), loglvl);
        //初始化TLS
        TlsBusiness.init(getApplicationContext());
        String id = TLSService.getInstance().getLastUserIdentifier();
        UserInfo.getInstance().setId(id);
        UserInfo.getInstance().setUserSig(TLSService.getInstance().getUserSig(id));
        Log.d(TAG, "id = " + id + "---UserSig = " + TLSService.getInstance().getUserSig(id));
//        presenter = new SplashPresenter(this);
//        presenter.start();
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
                Intent intent = new Intent(LoginActivity.this, DialogActivity.class);
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
                        Toast.makeText(LoginActivity.this, "onConnected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDisconnected(int code, String desc) {
                        Log.i(TAG, "onDisconnected");
                        Toast.makeText(LoginActivity.this, "onDisconnected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onWifiNeedAuth(String name) {
                        Toast.makeText(LoginActivity.this, "onWifiNeedAuth", Toast.LENGTH_SHORT).show();
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
}
