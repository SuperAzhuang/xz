package com.xiaozhao.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

import com.xiaozhao.activity.CompanyActivity;
import com.xiaozhao.activity.LoginActivity;
import com.xiaozhao.activity.MainActivity;
import com.xiaozhao.activity.RegistActivity;
import com.xiaozhao.activity.SearchActivity;
import com.xiaozhao.activity.SimpleBackActivity;
import com.xiaozhao.activity.SplashActivity;
import com.xiaozhao.bean.SimpleBackPage;
import com.xiaozhao.http.GlideImageLoader;
import com.xiaozhao.zxing.activity.CaptureActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

/**
 * 界面帮助类
 */
public class UIHelper {

    /**
     * 显示主界面
     *
     * @param context
     */
    public static void showMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        // intent.putExtra(SplashActivity.START_FROM_NOTIFICATION, 1);
        context.startActivity(intent);
    }


    public static void initWebView(final Activity context, WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
//				CommonDialog dialog = new CommonDialog(context);
//				dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						dialog.dismiss();
//						result.confirm();
//					}
//				});
//				dialog.setMessage(message);
//				dialog.setCanceledOnTouchOutside(true);
//				dialog.setCancelable(true);
//				dialog.show();
                return true;
            }
        });
    }


    public static void initBaners(ArrayList<String> mImageLists,  Banner banner) {
        //设置banner样式
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(mImageLists);
        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(mTitleLists);
//        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }


    /**
     * 显示登录界面
     *
     * @param context
     */
    public static void showLoginActivity(Context context) {

        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);

    }

    public static void showSearchActivity(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    public static void showCompanyActivity(Context context) {
        Intent intent = new Intent(context, CompanyActivity.class);
        context.startActivity(intent);
    }

    public static void showRegistActivity(Context context) {
        Intent intent = new Intent(context, RegistActivity.class);
        context.startActivity(intent);
    }

    public static void showCaptureActivity(Context context) {
        Intent intent = new Intent(context, CaptureActivity.class);
        context.startActivity(intent);
    }
    public static void showSimpleBack(Context context, SimpleBackPage page, Bundle args) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivity(intent);
    }
    public static void showSimpleBackForResult(Fragment fragment, int requestCode, SimpleBackPage page, Bundle args) {
        Intent intent = new Intent(fragment.getActivity(), SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        fragment.startActivityForResult(intent, requestCode);
    }
}
