package com.xiaozhao.fragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.xiaozhao.R;
import com.xiaozhao.adapter.AppliAdapter;
import com.xiaozhao.adapter.HomeCompanyAdapter;
import com.xiaozhao.adapter.HomeNearApplerAdapter;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.bean.NewsResult;
import com.xiaozhao.http.AsyncHttpApi;
import com.xiaozhao.http.Url;
import com.xiaozhao.manager.DividerApplerItemDecoration;
import com.xiaozhao.manager.DividerItemDecoration;
import com.xiaozhao.utils.LogUtils;
import com.xiaozhao.utils.UIHelper;
import com.xiaozhao.view.EmptyLayout;
import com.xiaozhao.view.SelectPopupWindow;
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

    @InjectView(R.id.error_layout)
    EmptyLayout mErrorLayout;
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
    @InjectView(R.id.rv_list)
    RecyclerView rvList;
    @InjectView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
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
    private HomeNearApplerAdapter mAdapter;
    boolean isSelected1 = true;
    boolean isSelected2 = true;
    boolean isSelected3 = true;

    private String[] parentStrings = {"全城", "中原区", "二七区", "管城区", "金水区", "上街区", "惠济区", "郑东新区", "高新区", "经开区", "郑州周边"};
    private String[][] childrenStrings = {{},
            {"中原1", "中原2", "中原3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"二七1", "二七2", "二七3", "二七4", "二七5", "二七6", "二七7", "二七8", "二七9", "二七10", "二七11", "二七12", "二七13", "二七14", "二七15"},
            {"管城1", "管城2", "管城3", "管城4", "管城5", "管城6", "管城7", "管城8", "管城9", "管城10", "管城11", "管城12", "管城13", "管城14", "管城15"},
            {"金水1", "金水2", "金水3", "金水4", "金水5", "金水6", "金水7", "金水8", "金水9", "金水10", "金水11", "金水12", "金水13", "金水14", "金水15"},
            {"上街1", "上街2", "上街3", "上街4", "上街5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"中原1", "中原2", "中原3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"郑东新区1", "郑东新区2", "郑东新区3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"高新区1", "高新区2", "高新区3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"经开区1", "经开区2", "经开区3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"周边1", "周边2", "周边3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.near_appyler_fragment, container, false);

        ButterKnife.inject(this, view);
        View parent = getActivity().getWindow().getDecorView();
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
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        initData();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tvJuli:
                ivJuli.setImageResource(isSelected3 ? R.mipmap.drop_down_selected_icon : R.mipmap.drop_down_unselected_icon);
                isSelected3 = !isSelected3;
                break;

            case R.id.tvJob:
//                View parent = getActivity().getWindow().getDecorView();
//                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
//                Window w = getActivity().getWindow();

                SelectPopupWindow shopPopuWindow1 = new SelectPopupWindow(parentStrings, childrenStrings, getMAinActivity(), selectCategory);
                shopPopuWindow1.setAnimationStyle(R.style.PopupAnimation);
                if (shopPopuWindow1 != null && shopPopuWindow1.isShowing()) {
                    shopPopuWindow1.dismiss();
                }
                if (ll != null) {
                    shopPopuWindow1.showAsDropDown(ll);
                }
                ivCompany.setImageResource(isSelected1 ? R.mipmap.drop_down_selected_icon : R.mipmap.drop_down_unselected_icon);
                isSelected1 = !isSelected1;
                shopPopuWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ivCompany.setImageResource(R.mipmap.drop_down_unselected_icon);
                        isSelected1 = true;
                    }
                });


                break;

            case R.id.tvMore:

                SelectPopupWindow shopPopuWindow2 = new SelectPopupWindow(parentStrings, childrenStrings, getMAinActivity(), selectCategory);
                if (shopPopuWindow2 != null && shopPopuWindow2.isShowing()) {
                    shopPopuWindow2.dismiss();
                }
                shopPopuWindow2.setAnimationStyle(R.style.PopupAnimation);
                shopPopuWindow2.showAsDropDown(ll);
                ivMore.setImageResource(isSelected2 ? R.mipmap.drop_down_selected_icon : R.mipmap.drop_down_unselected_icon);
                isSelected2 = !isSelected2;
                shopPopuWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ivMore.setImageResource(R.mipmap.drop_down_unselected_icon);
                        isSelected2 = true;
//                        lp.alpha = 1;
//                        w.setAttributes(lp);
                    }
                });
                break;

        }
    }

    @Override
    public void initView(View view) {

        rvList.setLayoutManager(new GridLayoutManager(getApplication(), 3));
        rvList.addItemDecoration(new DividerApplerItemDecoration(getApplication()));
        mAdapter = new HomeNearApplerAdapter(R.layout.apply_grid_item, mDatas);

//        companyGridAdapter.addHeaderView(getListViewHeadView());
        mAdapter.setLoadMoreView(new SimpleLoadMoreView());
        rvList.setAdapter(mAdapter);

        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setRefreshing(true);
        tvJuli.setOnClickListener(this);
        tvJob.setOnClickListener(this);
        tvMore.setOnClickListener(this);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                UIHelper.showCompanyActivity(getActivity());
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
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

                    mAdapter.loadMoreComplete();
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
            Toast.makeText(getApplication(), "加载失败", Toast.LENGTH_SHORT).show();
            if (mCurrentPage >= 2) {
//                companyGridAdapter.noti
                mAdapter.loadMoreFail();
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
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mAdapter.setEnableLoadMore(true);
        if (mCurrentPage == 1) {
            mDatas.clear();
        }
        mAdapter.addData(data);

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
    /**
     * 选择完成回调接口
     */
    private SelectPopupWindow.SelectCategory selectCategory = new SelectPopupWindow.SelectCategory() {
        @Override
        public void selectCategory(int parentSelectposition, int childrenSelectposition) {
            String parentStr = parentStrings[parentSelectposition];
            String childrenStr = childrenStrings[parentSelectposition][childrenSelectposition];

            Toast.makeText(getActivity(), "父类别:" + parentStr + "  子类别:" + childrenStr, Toast.LENGTH_SHORT).show();
        }
    };

}
