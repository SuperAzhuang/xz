package com.xiaozhao.http;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.Logger;
import com.xiaozhao.utils.LogUtils;

public class ApiHttpClient {


    //广电
//	public static final int ITEMS = 20;
//	public static final String HOSTS = "http://iqgtv.com/android/";
//	public static final String ZHIBO = "http://iqgtv.com/android/zhiboyuan.php";
//	public static final String Weidiantai = "http://iqgtv.com/weidiantai.php";
//	public static final String Yueshenghuo = "http://iqgtv.com/yueshenghuo.php";
//	public static final String Tiezi = "http://iqgtv.com/bbs/upload/getHotAticleList.php";
//	http://iqgtv.com/android/quangangxinwen.php?items=20&page=1
    public static String API_URL = "http://z.zhijingcai.cn/%s";// 真实环境
    //	public static String API_URL = "http://iqgtv.com/android/%s";// 真实环境
    public static AsyncHttpClient client;

    public ApiHttpClient() {
    }

    public static AsyncHttpClient getHttpClient() {
        return client;
    }

    public static void cancelAll(Context context) {
        client.cancelRequests(context, true);
    }

    public static void clearUserCookies(Context context) {
        // (new HttpClientCookieStore(context)).a();
    }

    public static void delete(String partUrl, AsyncHttpResponseHandler handler) {
        client.delete(getAbsoluteApiUrl(partUrl), handler);
        log(new StringBuilder("DELETE ").append(partUrl).toString());
    }

    public static void get(String partUrl, AsyncHttpResponseHandler handler) {
        client.get(getAbsoluteApiUrl(partUrl), handler);
        log(new StringBuilder("GET ").append(partUrl).toString());
    }

    public static void get(String partUrl, RequestParams params, AsyncHttpResponseHandler handler) {
        client.get(getAbsoluteApiUrl(partUrl), params, handler);
        log(new StringBuilder("GET ").append(getAbsoluteApiUrl(partUrl)).append("&").append(params).toString());
    }

    public static String getAbsoluteApiUrl(String partUrl) {
        String url = String.format(API_URL, partUrl);
        Logger.t("BASE_CLIENT").d(url);
        return url;
    }

    public static String getApiUrl() {
        return API_URL;
    }

    public static void getDirect(String url, AsyncHttpResponseHandler handler) {
        client.get(url, handler);
        log(new StringBuilder("GET ").append(url).toString());
    }

    public static void log(String log) {
//		TLog.debug("BaseApi", log);1
        LogUtils.d(log);
    }

    public static void post(String partUrl, AsyncHttpResponseHandler handler) {
        client.post(getAbsoluteApiUrl(partUrl), handler);
        log(new StringBuilder("POST ").append(getAbsoluteApiUrl(partUrl)).toString());
    }

    public static void post(String partUrl, RequestParams params, AsyncHttpResponseHandler handler) {
        String absoluteApiUrl = getAbsoluteApiUrl(partUrl);
        client.post(absoluteApiUrl, params, handler);
        log(new StringBuilder("POST ").append(getAbsoluteApiUrl(partUrl)).append("&").append(params).toString());

    }

    public static void postBanner(String bannerUrl, AsyncHttpResponseHandler handler) {
        client.post("http://www.wanandroid.com/banner/json", handler);
//		log(new StringBuilder("POST ").append(getAbsoluteApiUrl(partUrl)).append("&").append(params).toString());

    }

    public static void postDirect(String url, RequestParams params, AsyncHttpResponseHandler handler) {
        client.post(url, params, handler);
        log(new StringBuilder("POST ").append(url).append("&").append(params).toString());
    }

    public static void put(String partUrl, AsyncHttpResponseHandler handler) {
        client.put(getAbsoluteApiUrl(partUrl), handler);
        log(new StringBuilder("PUT ").append(partUrl).toString());
    }

    public static void put(String partUrl, RequestParams params, AsyncHttpResponseHandler handler) {
        client.put(getAbsoluteApiUrl(partUrl), params, handler);
        log(new StringBuilder("PUT ").append(partUrl).append("&").append(params).toString());
    }

    public static void setApiUrl(String apiUrl) {
        API_URL = apiUrl;
    }

    public static void setHttpClient(AsyncHttpClient c) {
        client = c;
    }

    public static void setUserAgent(String userAgent) {
        client.setUserAgent(userAgent);
    }

    public static void setCookie(String cookie) {
        client.addHeader("Cookie", cookie);
    }

    private static String appCookie;


}
