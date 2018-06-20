package com.xiaozhao.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.fastjson.JSON;
import com.xiaozhao.R;
import com.xiaozhao.activity.MainActivity;
import com.xiaozhao.adapter.NewsListAdapter;
import com.xiaozhao.base.BaseListFragment;
import com.xiaozhao.base.ListBaseAdapter;
import com.xiaozhao.bean.ListEntity;
import com.xiaozhao.bean.NewsResult;
import com.xiaozhao.http.XiaoZhaoHttpApi;
import com.xiaozhao.http.Url;
import com.xiaozhao.utils.LogUtils;
import com.xiaozhao.utils.UIHelper;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class NewsListFragment extends BaseListFragment<NewsResult.NewsBean> {

    public static final int CATALOG_RECOMMEND = 1;
    public static final int CATALOG_COMPLETE = 2;
    public static final int CATALOG_SEARCH_LECTURE = 3;
    public static final int CATALOG_MY_LECTURE = 4;
    private Banner banner;
    private String TYPE = Url.QUANGANGXINWEN;
    private String CACHE_PATH = "QUANGANGXINWEN";
    private List<NewsResult.NewsBean> list;
    private NewsResult.NewsBean newsBean;
    private ArrayList<String> mImageLists = new ArrayList<>();
    private ArrayList<String> mTitleLists = new ArrayList<>();

    @Override
    protected ListBaseAdapter<NewsResult.NewsBean> getListAdapter() {

        return new NewsListAdapter();
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void initData() {

    }

    /**
     * 轮播图头布局添加到Listview的头
     */
    @Override
    public View getListViewHeadView() {
//        轮播图头布局添加到Listview的头
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.news_banner, null);
        banner = view.findViewById(R.id.banner);

        return view;
    }

    /**
     * 请求网络数据
     */
    @Override
    protected void sendRequestData() {
        super.sendRequestData();
//        if (mCatalog == CATALOG_RECOMMEND)
        XiaoZhaoHttpApi.getNewsLists(mCurrentPage, mHandler, TYPE);
//        loadData(getCommonUrls(TYPE,(index + "")));
    }

    @Override
    protected ListEntity<NewsResult.NewsBean> parseList(String is) throws Exception {

        LogUtils.d(is);
        NewsResult result = JSON.parseObject(is, NewsResult.class);
        list = result.getList();
        mImageLists.clear();
        mTitleLists.clear();
//        LogUtils.printList(list);
        for (int i = 0; i < 4; i++) {
            newsBean = list.get(i);
//            轮播图的图片跟新闻标题
            mImageLists.add(newsBean.getLitpic());
            mTitleLists.add(newsBean.getTitle());
        }

        ((MainActivity) getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                初始化banner配置
                UIHelper.initBaners(mImageLists, banner);
            }
        });
        return result;
    }

    /**
     * 缓存路劲
     *
     * @return
     */
    @Override
    protected String getCacheKeyPrefix() {
        return "newsList" + mCatalog + "_" + mCurrentPage;
    }

}
