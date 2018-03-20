package com.xiaozhao.http;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xiaozhao.utils.LogUtils;

public class ApiHttpClient {


	//广电
	public static final int ITEMS = 20;
	public static final String HOSTS = "http://iqgtv.com/android/";
	public static final String ZHIBO = "http://iqgtv.com/android/zhiboyuan.php";
	public static final String Weidiantai = "http://iqgtv.com/weidiantai.php";
	public static final String Yueshenghuo = "http://iqgtv.com/yueshenghuo.php";
	public static final String Tiezi = "http://iqgtv.com/bbs/upload/getHotAticleList.php";
//	http://iqgtv.com/android/quangangxinwen.php?items=20&page=1

	public static final String CHONGZHI = "https://www.baifubao.com/wap/0/charge/0?channel_no=CHF0000004";
	public static final String TUANGOU = "http://i.meituan.com/";
	public static final String JIUDIAN = "http://m.ctrip.com/html5/";
	public static final String JD = "http://m.jd.com";
	public static final String XIAOHUA = "http://m.lengxiaohua.com/";
	public static final String DIANYING = "http://m.dianping.com/tuan/quanzhou/list/2_0_10_0";
	public static final String TUNIU = "http://dynamic.m.tuniu.com";
	public static final String TIANQI = "http://m.nmc.cn/publish/forecast/AFJ/quanzhou.html";
	public static final String XIAMI = "http://h.xiami.com/#!/chart/101";

	//登录
	public static final String LOGIN = "http://iqgtv.com/member/login_user.php";

	//请求验证码
	public static final String MESCODE = "http://iqgtv.com/member/get_checkcode.php";

	//注册
	public static final String REGISTER = "http://iqgtv.com/member/register_newuser.php";

	//个人资料
	public static final String PROFILE = "http://iqgtv.com/member/member_view.php";

	//泉港新闻
	public static final String QUANGANGXINWEN = "quangangxinwen.php";

	//安全第一线
	public static final String ANQUANDIYIXIAN = "anquandiyixian.php";

	//搞笑趣图
	public static final String GAOXIAOQUTU = "gaoxiaoqutu.php";

	//好朋友
	public static final String HAOPENGYOU = "haopengyou.php";

	//汇聚经典
	public static final String HUIJUJINGDIAN = "huijujingdian.php";

	//交通与安全
	public static final String JIAOTONGYUANQUAN = "jiaotongyuanquan.php";

	//家泉港
	public static final String JIAQUANGANG = "jiaquangang.php";

	//聚焦专题
	public static final String JUJIAOZHUANTI = "jujiaozhuanti.php";

	//联办专题
	public static final String LIANBANZHUANTI = "lianbanzhuanti.php";

	//连线泉港
	public static final String LIANXIANQUANGANG = "lianxianquangang.php";

	//廉政之窗
	public static final String LIANZHENGZHICHUANG = "lianzhengzhichuang.php";

	//泉港讲古
	public static final String QUANGANGJIANGGU = "quangangjianggu.php";

	//特别策划
	public static final String TEBIECEHUA = "tebiecehua.php";

	//图说泉港
	public static final String TUSHUOQUANGANG = "tushuoquangang.php";

	//图说天下
	public static final String TUSHUOTIANXIA = "tushuotianxia.php";

	//业界动态
	public static final String YEJIEDONGTAI = "yejiedongtai.php";

	//综合资讯
	public static final String ZONGHEZIXUN = "zonghezixun.php";


	//泉港新闻 详情
	public static final String QUANGANGXINWEN_DETAIL = "quangangxinwendetail.php";

	//安全第一线  详情
	public static final String ANQUANDIYIXIAN_DETAIL = "anquandiyixiandetail.php";

	//搞笑趣图  详情
	public static final String GAOXIAOQUTU_DETAIL = "gaoxiaoqutudetail.php";

	//好朋友  详情
	public static final String HAOPENGYOU_DETAIL = "haopengyoudetail.php";

	//汇聚经典 详情
	public static final String HUIJUJINGDIAN_DETAIL = "huijujingdiandetail.php";

	//交通与安全  详情
	public static final String JIAOTONGYUANQUAN_DETAIL = "jiaotongyuanquandetail.php";

	//家泉港  详情
	public static final String JIAQUANGANG_DETAIL = "jiaquangangdetail.php";

	//聚焦专题 详情
	public static final String JUJIAOZHUANTI_DETAIL = "jujiaozhuantidetail.php";

	//联办专题 详情
	public static final String LIANBANZHUANTI_DETAIL = "lianbanzhuantidetail.php";

	//连线泉港  详情
	public static final String LIANXIANQUANGANG_DETAIL = "lianxianquangangdetail.php";

	//廉政之窗  详情
	public static final String LIANZHENGZHICHUANG_DETAIL = "lianzhengzhichuangdetail.php";

	//泉港讲古 详情
	public static final String QUANGANGJIANGGU_DETAIL = "quangangjianggudetail.php";

	//特别策划  详情
	public static final String TEBIECEHUA_DETAIL = "tebiecehuadetail.php";

	//图说泉港  详情
	public static final String TUSHUOQUANGANG_DETAIL = "tushuoquangangdetail.php";

	//图说天下  详情
	public static final String TUSHUOTIANXIA_DETAIL = "tushuotianxiadetail.php";

	//业界动态  详情
	public static final String YEJIEDONGTAI_DETAIL = "yejiedongtaidetail.php";

	//综合资讯 详情
	public static final String ZONGHEZIXUN_DETAIL = "zonghezixundetail.php";



//	public static String API_URL = "http://appapi.duduliuxue.com/%s";// 真实环境
	public static String API_URL = "http://iqgtv.com/android/%s";// 真实环境
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
		Log.d("BASE_CLIENT", "request:" + url);
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
