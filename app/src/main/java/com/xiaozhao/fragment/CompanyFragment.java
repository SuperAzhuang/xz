package com.xiaozhao.fragment;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.orhanobut.logger.Logger;
import com.xiaozhao.R;
import com.xiaozhao.activity.MainActivity;
import com.xiaozhao.adapter.CompanyGridAdapter;
import com.xiaozhao.adapter.HomeCompanyAdapter;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.bean.NewsResult;
import com.xiaozhao.http.AsyncHttpApi;
import com.xiaozhao.http.Url;
import com.xiaozhao.utils.LogUtils;
import com.xiaozhao.utils.UIHelper;
import com.xiaozhao.view.EmptyLayout;
import com.xiaozhao.view.MyGridView;
import com.xiaozhao.view.TagPopwindow;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONObject;

import java.util.ArrayList;
import  com.xiaozhao.manager.DividerItemDecoration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

import static com.orhanobut.logger.Logger.json;
import static com.orhanobut.logger.Logger.t;
import static com.xiaozhao.R.id.recyclerView;


/**
 * 附近企业
 */
public class CompanyFragment extends BaseFragment {

    @InjectView(R.id.tvJuli)
    TextView tvJuli;
    @InjectView(R.id.tvCompany)
    TextView tvCompany;
    @InjectView(R.id.tvMore)
    TextView tvMore;
    @InjectView(R.id.rv_list)
    RecyclerView grideview;
    @InjectView(R.id.error_layout)
    EmptyLayout mErrorLayout;
    @InjectView(R.id.ivJuli)
    ImageView ivJuli;
    @InjectView(R.id.ivCompany)
    ImageView ivCompany;
    @InjectView(R.id.ivMore)
    ImageView ivMore;
    @InjectView(R.id.llJuli)
    LinearLayout llJuli;
    @InjectView(R.id.ll)
    LinearLayout ll;
    @InjectView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ParserTask mParserTask;
    private List<NewsResult.NewsBean> list;
    private List<NewsResult.NewsBean> mDatas = new ArrayList<>();
    private NewsResult.NewsBean newsBean;


    protected int mCurrentPage = 1;
    private String TYPE = Url.QUANGANGXINWEN;
    private ArrayList<String> mImageLists = new ArrayList<>();
    private ArrayList<String> mTitleLists = new ArrayList<>();
    private BaseQuickAdapter companyGridAdapter;
    private TagPopwindow shopPopuWindow;
    private WindowManager.LayoutParams lp;
    private Window w;
    private String LogTag = CompanyFragment.class.getName();
    private Banner banner;

    /**
     * 轮播图头布局添加到Listview的头
     */
    public View getListViewHeadView() {
//        轮播图头布局添加到Listview的头

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.news_banner, null);
        banner = view.findViewById(R.id.banner);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                UIHelper.showCompanyActivity(getActivity());
            }
        });
//        LogUtils.d("getListViewHeadView = " + view);  181,181,181
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);
        ButterKnife.inject(this, view);
        lp = getActivity().getWindow().getAttributes();
        w = getActivity().getWindow();
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        return view;
    }

    private void refresh() {
        mCurrentPage = 1;
        companyGridAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        initData();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvJuli:

                break;

            case R.id.tvCompany:

//                View parent = getActivity().getWindow().getDecorView();
//                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
//                Window w = getActivity().getWindow();
//
                shopPopuWindow = new TagPopwindow(getActivity(), lp, w, null);

                if (shopPopuWindow != null && shopPopuWindow.isShowing()) {
                    shopPopuWindow.dismiss();
                }
                if (ll != null) {
                    shopPopuWindow.showAsDropDown(ll);
                }
                break;

            case R.id.tvMore:
                shopPopuWindow = new TagPopwindow(getActivity(), lp, w, null);
                if (shopPopuWindow != null && shopPopuWindow.isShowing()) {
                    shopPopuWindow.dismiss();
                }
                shopPopuWindow = new TagPopwindow(getActivity(), lp, w, null);
                shopPopuWindow.showAsDropDown(ll);
                break;

        }
    }

    @Override
    public void initView(View view) {

        grideview.setLayoutManager(new GridLayoutManager(getApplication(), 2));
        grideview.addItemDecoration(new DividerItemDecoration(getApplication()));
        companyGridAdapter = new HomeCompanyAdapter(R.layout.company_grid_item, mDatas);

        companyGridAdapter.addHeaderView(getListViewHeadView());
        companyGridAdapter.setLoadMoreView(new SimpleLoadMoreView());
        grideview.setAdapter(companyGridAdapter);

        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setRefreshing(true);

        tvJuli.setOnClickListener(this);
        tvCompany.setOnClickListener(this);
        tvMore.setOnClickListener(this);
        companyGridAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                UIHelper.showCompanyActivity(getActivity());
            }
        });
        companyGridAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mSwipeRefreshLayout.setRefreshing(false);
                mCurrentPage++;
                initData();

            }
        });

    }

    @Override
    public void initData() {
//        mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        AsyncHttpApi.getNewsLists(mCurrentPage, mHandler, TYPE);
    }

    protected AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBytes) {


//            if (mCurrentPage == 1 && needAutoRefresh()) {
//                BaseApplication.putToLastRefreshTime(getCacheKey(), StringUtils.getCurTimeStr());
//            }
//            if (isAdded()) {
//                if (mState == STATE_REFRESH) {
//                    onRefreshNetworkSuccess();
//                }
            executeParserTask(responseBytes);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    companyGridAdapter.loadMoreComplete();
                }
            }, 500);

//            } else {
//                executeOnLoadDataError("");
//            }

        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
//			TLog.log("dudutime", TAG + "请求失败:" + (System.currentTimeMillis() - mCurrentTimeMillis));
//            if (isAdded()) {
//                readCacheData(getCacheKey());
//            } else {
//                executeOnLoadDataError("");
//            }
            Toast.makeText(getApplication(),"加载失败",Toast.LENGTH_SHORT).show();
            if (mCurrentPage>=2) {
//                companyGridAdapter.noti
                companyGridAdapter.loadMoreFail();
//                companyGridAdapter.loadMoreComplete();
//                companyGridAdapter.loadMoreEnd();
            }
        }
    };

    private void executeParserTask(byte[] data) {
        cancelParserTask();
        mParserTask = new ParserTask(data);
        mParserTask.execute();
    }

    private void cancelParserTask() {
        if (mParserTask != null) {
            mParserTask.cancel(true);
            mParserTask = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    class ParserTask extends AsyncTask<Void, Void, String> {

        private final byte[] reponseData;
        private boolean parserError = false;
        private List<NewsResult.NewsBean> list;
        private int status;

        public ParserTask(byte[] data) {
            this.reponseData = data;
        }

        @Override
        protected String doInBackground(Void... params) {
            String is = new String(reponseData);

            Logger.t(CompanyFragment.class.getName()).d("debug");
            Logger.t(LogTag).json(is);
            // try {
            // // new
            try {
                if (is.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(is);
                    status = jsonObject.optInt("status");
                    if (status == 403)
                        return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//
//            ListEntity<T> data = null;
            try {
                list = parseList(is);
//                if (data == null) {
//                    return null;
//                }
//                list = data.getList();
//
//                String cacheKey = getCacheKey();
//                if (!TextUtils.isEmpty(cacheKey))
//                    new BaseListFragment.SaveCacheTask(getActivity(), data, cacheKey).execute();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                parserError = true;
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
//            解析出错
            if (parserError) {

//                String cacheKey = getCacheKey();
//                if (!TextUtils.isEmpty(cacheKey)) {
//                    readCacheData(cacheKey);
//                } else {
//                    if (needShowEmptyNoData()) {
//                        mErrorLayout.setErrorType(EmptyLayout.NODATA);
//                    } else {
//                        mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
//                        mAdapter.setState(ListBaseAdapter.STATE_NETWORK_ERROR);
//                    }
//                }
            } else {
                executeOnLoadDataSuccess(list);
            }

        }
    }

    protected List<NewsResult.NewsBean> parseList(String is) throws Exception {

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
        return list;
    }

    protected void executeOnLoadDataSuccess(List<NewsResult.NewsBean> data) {
        if (mSwipeRefreshLayout!=null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        companyGridAdapter.setEnableLoadMore(true);
        if (mCurrentPage == 1) {
            mDatas.clear();
        }
        companyGridAdapter.addData(data);

        if (mErrorLayout != null) {
            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        }
//        if (mCurrentPage == 1) {
//            mAdapter.clear();
//        }
//
//        int adapterState = ListBaseAdapter.STATE_EMPTY_ITEM;
//        if ((mAdapter.getCount() + data.size()) == 0) {
//            adapterState = ListBaseAdapter.STATE_EMPTY_ITEM;
//        } else if (data.size() == 0 || (data.size() < getPageSize() && mCurrentPage == 1) || (mTotalPage != -1 && mCurrentPage >= mTotalPage)) {
//            adapterState = ListBaseAdapter.STATE_NO_MORE;
//            mAdapter.notifyDataSetChanged();
//        } else {
//            adapterState = ListBaseAdapter.STATE_LOAD_MORE;
//        }
//        mAdapter.setState(adapterState);
//        mAdapter.addData(data);
//        // 判断等于是因为最后有一项是listview的状态
//        if (mAdapter.getCount() == 1) {
//
//            if (needShowEmptyNoData()) {
//                mErrorLayout.setErrorType(EmptyLayout.NODATA);
//            } else {
//                mAdapter.setState(ListBaseAdapter.STATE_EMPTY_ITEM);
//                mAdapter.notifyDataSetChanged();
//            }
//        }
    }


}
