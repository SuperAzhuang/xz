package com.xiaozhao.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.xiaozhao.R;
import com.xiaozhao.adapter.AppliAdapter;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.bean.NewsResult;
import com.xiaozhao.http.AsyncHttpApi;
import com.xiaozhao.http.Url;
import com.xiaozhao.utils.LogUtils;
import com.xiaozhao.view.EmptyLayout;
import com.xiaozhao.view.MyGridView;
import com.xiaozhao.view.TagPopwindow;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2018/1/26.
 */

public class NearApplerFragment extends BaseFragment {

    @InjectView(R.id.tvJuli)
    TextView tvJuli;
    @InjectView(R.id.ivJuli)
    ImageView ivJuli;
    @InjectView(R.id.llJuli)
    LinearLayout llJuli;
    @InjectView(R.id.tvJob)
    TextView tvJob;
    @InjectView(R.id.ivCompany)
    ImageView ivCompany;
    @InjectView(R.id.tvMore)
    TextView tvMore;
    @InjectView(R.id.ivMore)
    ImageView ivMore;
    @InjectView(R.id.ll)
    LinearLayout ll;
    @InjectView(R.id.grideview)
    MyGridView grideview;
    @InjectView(R.id.error_layout)
    EmptyLayout mErrorLayout;
    private ParserTask mParserTask;
    private List<NewsResult.NewsBean> list;
    private List<NewsResult.NewsBean> mDatas = new ArrayList<>();
    private NewsResult.NewsBean newsBean;
    private TagPopwindow shopPopuWindow;
    private WindowManager.LayoutParams lp;
    private Window w;
    protected int mCurrentPage = 1;
    private String TYPE = Url.QUANGANGXINWEN;
    private ArrayList<String> mImageLists = new ArrayList<>();
    private ArrayList<String> mTitleLists = new ArrayList<>();
    private AppliAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhibo_fragment, container, false);
        ButterKnife.inject(this, view);
        View parent = getActivity().getWindow().getDecorView();
              lp = getActivity().getWindow().getAttributes();
              w = getActivity().getWindow();
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvJuli:

                break;

            case R.id.tvJob:

//                View parent = getActivity().getWindow().getDecorView();
//                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
//                Window w = getActivity().getWindow();
//
                shopPopuWindow = new TagPopwindow(getActivity(),lp,w,null);

                if (shopPopuWindow.isShowing()) {
                    shopPopuWindow.dismiss();
                }
                if (ll!=null ) {
                    shopPopuWindow.showAsDropDown(ll);
                }
                break;

            case R.id.tvMore:
                shopPopuWindow = new TagPopwindow(getActivity(),lp,w,null);
                if (shopPopuWindow.isShowing()) {
                    shopPopuWindow.dismiss();
                }
                shopPopuWindow = new TagPopwindow(getActivity(),lp,w,null);
                shopPopuWindow.showAsDropDown(ll);
                break;

        }
    }

    @Override
    public void initView(View view) {
        mAdapter = new AppliAdapter(getActivity(), mDatas);
        grideview.setAdapter(mAdapter);

        tvJuli.setOnClickListener(this);
        tvJob.setOnClickListener(this);
        tvMore.setOnClickListener(this);

    }

    @Override
    public void initData() {

        mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
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
//        LogUtils.printList(list);


        return list;
    }

    protected void executeOnLoadDataSuccess(List<NewsResult.NewsBean> data) {
//        if (data == null) {
//            data = new ArrayList<T>();
//        }
        LogUtils.d("List<NewsResult.NewsBean>  = " + data.toString());
        mDatas.clear();
        mDatas.addAll(data);
        mAdapter.notifyDataSetChanged();
        if (mErrorLayout!=null) {
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
