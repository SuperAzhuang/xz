package com.xiaozhao.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.imsdk.TIMGroupReceiveMessageOpt;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMOfflinePushListener;
import com.tencent.imsdk.TIMOfflinePushNotification;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.qalsdk.QALSDKManager;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qcloud.presentation.business.InitBusiness;
import com.tencent.qcloud.tlslibrary.service.TLSService;
import com.tencent.qcloud.tlslibrary.service.TlsBusiness;
import com.xiaozhao.BuildConfig;
import com.xiaozhao.R;
import com.xiaozhao.http.ApiHttpClient;
import com.xiaozhao.im.Foreground;
import com.xiaozhao.im.PushUtil;
import com.xiaozhao.im.UserInfo;
import com.xiaozhao.utils.StringUtils;

import org.litepal.LitePalApplication;

import java.util.ArrayList;
import java.util.List;

import tencent.tls.platform.TLSHelper;

import static com.tencent.qalsdk.service.QalService.context;


//LitePalApplication数据库配置
@SuppressLint("InflateParams")
public class BaseApplication extends LitePalApplication {
    private static String PREF_NAME = "creativelocker.pref";
    private static String LAST_REFRESH_TIME = "last_refresh_time.pref";
    static Context _context;
    static Resources _resource;
    private static String lastToast = "";
    private static long lastToastTime;
    private TLSHelper tlsHelper;
    private final int REQUEST_PHONE_PERMISSIONS = 0;
    private static boolean sIsAtLeastGB;
    private static Toast mToast;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            sIsAtLeastGB = true;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _context = getApplicationContext();
        _resource = _context.getResources();


        //初始化程序后台后消息推送
        PushUtil.getInstance();

        init();
        Logger.addLogAdapter(new AndroidLogAdapter());

        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

//IM配置
        initIm();

    }

    private void initIm() {

        Foreground.init(this);
        if (MsfSdkUtils.isMainProcess(this)) {
            TIMManager.getInstance().setOfflinePushListener(new TIMOfflinePushListener() {
                @Override
                public void handleNotification(TIMOfflinePushNotification notification) {
                    if (notification.getGroupReceiveMsgOpt() == TIMGroupReceiveMessageOpt.ReceiveAndNotify) {
                        //消息被设置为需要提醒
                        notification.doNotify(getApplicationContext(), R.mipmap.ic_launcher);
                    }
                }
            });
        }
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        int loglvl = pref.getInt("loglvl", TIMLogLevel.DEBUG.ordinal());
        //初始化IMSDK
        InitBusiness.start(getApplicationContext(), loglvl);
        //初始化TLS
        TlsBusiness.init(getApplicationContext());
        String id = TLSService.getInstance().getLastUserIdentifier();
        UserInfo.getInstance().setId(id);
        UserInfo.getInstance().setUserSig(TLSService.getInstance().getUserSig(id));
        Log.d("TAG", "id = " + id + "---UserSig = " + TLSService.getInstance().getUserSig(id));

        int sdkAppid = 1400077940;
        //初始化 SDK 基本配置
        TIMSdkConfig config = new TIMSdkConfig(sdkAppid)
                .enableCrashReport(false)
                .enableLogPrint(true);

        TIMManager.getInstance().init(getApplicationContext(), config);
// 务必检查IMSDK已做以下初始化
//        QALSDKManager.getInstance().setEnv(0);
        QALSDKManager.getInstance().init(_context, sdkAppid);

    }


    private void init() {
        // 初始化网络请求 和图片
        AsyncHttpClient client = new AsyncHttpClient();
        ApiHttpClient.setHttpClient(client);
        initImageLoad();

    }

    public static Context getContext() {
        return _context;
    }

    private void initImageLoad() {
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(this).memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13).threadPoolSize(3)
                // 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2).discCacheSize(50 * 1024 * 1024).discCacheFileCount(500)

                .discCacheFileNameGenerator(new Md5FileNameGenerator()).denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)).defaultDisplayImageOptions(DisplayImageOptions.createSimple());

        ImageLoaderConfiguration auild = builder.build();
        ImageLoader.getInstance().init(auild);
    }

    public static synchronized BaseApplication context() {
        return (BaseApplication) _context;
    }

    public static Resources resources() {
        return _resource;
    }

    /***
     * 记录列表上次刷新时间 2015-2-9 下午2:21:37
     *
     * @param key
     * @param value
     * @return void
     */
    public static void putToLastRefreshTime(String key, String value) {
        SharedPreferences preferences = getPreferences(LAST_REFRESH_TIME);
        Editor editor = preferences.edit();
        editor.putString(key, value);
        apply(editor);
    }

    /***
     * 获取列表的上次刷新时间 2015-2-9 下午2:22:04
     *
     * @param key
     * @return
     */
    public static String getLastRefreshTime(String key) {
        return getPreferences(LAST_REFRESH_TIME).getString(key, StringUtils.getCurTimeStr());
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void apply(Editor editor) {
        if (sIsAtLeastGB) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public static void set(String key, boolean value) {
        Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        apply(editor);
    }

    public static void set(String key, String value) {
        Editor editor = getPreferences().edit();
        editor.putString(key, value);
        apply(editor);
    }

    public static void set(String key, int value) {
        Editor editor = getPreferences().edit();
        editor.putInt(key, value);
        apply(editor);
    }

    public static boolean get(String key, boolean defValue) {
        return getPreferences().getBoolean(key, defValue);
    }

    public static String get(String key, String defValue) {
        return getPreferences().getString(key, defValue);
    }

    public static int get(String key, int defValue) {
        return getPreferences().getInt(key, defValue);
    }

    public static long get(String key, long defValue) {
        return getPreferences().getLong(key, defValue);
    }

    public static float get(String key, float defValue) {
        return getPreferences().getFloat(key, defValue);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static SharedPreferences getPreferences() {
        SharedPreferences pre = context().getSharedPreferences(PREF_NAME, Context.MODE_MULTI_PROCESS);
        return pre;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static SharedPreferences getPreferences(String prefName) {
        return context().getSharedPreferences(prefName, Context.MODE_MULTI_PROCESS);
    }

    public static int[] getDisplaySize() {
        return new int[]{getPreferences().getInt("screen_width", 480), getPreferences().getInt("screen_height", 854)};
    }

    public static void saveDisplaySize(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        Editor editor = getPreferences().edit();
        editor.putInt("screen_width", displaymetrics.widthPixels);
        editor.putInt("screen_height", displaymetrics.heightPixels);
        editor.putFloat("density", displaymetrics.density);
        editor.commit();
    }

    public static String string(int id) {
        return _resource.getString(id);
    }

    public static String string(int id, Object... args) {
        return _resource.getString(id, args);
    }

    public static void showToast(int message) {
        showToast(message, Toast.LENGTH_LONG, 0);
    }

    public static void showToast(String message) {
        showToast(message, Toast.LENGTH_LONG, 0, Gravity.CENTER);
    }

    public static void showToast(int message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon);
    }

    public static void showToast(String message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon, 0);
    }

    public static void showToastShort(int message) {
        showToast(message, Toast.LENGTH_SHORT, 0);
    }

    public static void showToastShort(String message) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.CENTER);
    }

    public static void showToastShort(int message, Object... args) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.CENTER, args);
    }

    public static void showToast(int message, int duration, int icon) {
        showToast(message, duration, icon, Gravity.CENTER);
    }

    public static void showToast(int message, int duration, int icon, int gravity) {
        showToast(context().getString(message), duration, icon, gravity);
    }

    public static void showToast(int message, int duration, int icon, int gravity, Object... args) {
        showToast(context().getString(message, args), duration, icon, gravity);
    }

    public static void showToast(String message, int duration, int icon, int gravity) {
        if (message != null && !message.equalsIgnoreCase("")) {
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(lastToast) || Math.abs(time - lastToastTime) > 2000) {


                View view = LayoutInflater.from(context()).inflate(R.layout.view_toast, null);
                ((TextView) view.findViewById(R.id.title_tv)).setText(message);
                if (icon != 0) {
                    ((ImageView) view.findViewById(R.id.icon_iv)).setImageResource(icon);
                    ((ImageView) view.findViewById(R.id.icon_iv)).setVisibility(View.VISIBLE);
                }
                if (mToast == null) {
                    mToast = new Toast(context());
                }
                mToast.setView(view);
                if (gravity == Gravity.CENTER) {
                    mToast.setGravity(gravity, 0, 0);
                } else {
                    mToast.setGravity(gravity, 0, 35);
                }

                mToast.setDuration(duration);
                mToast.show();
                lastToast = message;
                lastToastTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
