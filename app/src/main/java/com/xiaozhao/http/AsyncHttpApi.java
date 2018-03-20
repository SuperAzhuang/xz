package com.xiaozhao.http;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class AsyncHttpApi {
    /**
     * 登陆
     */
    public static void login(String username, String password, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("login_email", username);
        params.put("login_password", password);
        String quangangxinwen = Url.QUANGANGXINWEN;
        ApiHttpClient.post(quangangxinwen, params, handler);
    }

    /**
     * 请求认证
     *
     * @param uidEncrypt 加密后的uid
     * @param pswEncrypt 加密后的密码
     */
    public static void requestAuthentication(String uidEncrypt, String pswEncrypt, AsyncHttpResponseHandler handler) {
        // "http://sns.myhqt.com/index.php?app=api&mod=Oauth&act=authorize"
        RequestParams params = new RequestParams();
        params.put("uid", uidEncrypt);
        params.put("password", pswEncrypt);
        String authorizeUrl = "index.php?app=api&mod=Oauth&act=authorize";
        ApiHttpClient.post(authorizeUrl, params, handler);
    }

    /**
     * 新闻页面
     *
     * @param mCurrentPage
     * @param mHandler
     * @param TYPE
     */
    public static void getNewsLists(int mCurrentPage, AsyncHttpResponseHandler mHandler, String type) {
        RequestParams params = new RequestParams();
        params.put("items", 20);
        params.put("page", mCurrentPage);
//        String urlString = Url.HOSTS + type + "?items=" + Url.ITEMS + "&page=" + index;
        ApiHttpClient.post(type, params, mHandler);
    }


    /**
     * 根据话题名称获取动态
     *
     */
//	public static void getPostList(String topicid, int page, AsyncHttpResponseHandler handler) {
//		String pathUrl = "index.php?app=api&mod=WeiboStatuses&act=topic_timeline";
//		RequestParams params = new RequestParams();
//		params.put("oauth_token_secret", AppContext.getInstance().getOauth_token_secret());
//		params.put("oauth_token", AppContext.getInstance().getOauth_token());
//		params.put("page", page + "");
//		params.put("topic_id", topicid);
//		ApiHttpClient.get(pathUrl, params, handler);
//	}


}
