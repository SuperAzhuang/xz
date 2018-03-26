package com.xiaozhao.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.xiaozhao.R;
import com.xiaozhao.adapter.CompanyInfoDetailAdapter;
import com.xiaozhao.base.BaseActivity;
import com.xiaozhao.bean.BannerInfo;
import com.xiaozhao.bean.NewsResult;
import com.xiaozhao.fragment.CompanyInfoDetailFragment;
import com.xiaozhao.http.ApiHttpClient;
import com.xiaozhao.utils.UIHelper;
import com.youth.banner.Banner;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

import static com.xiaozhao.adapter.CompanyInfoDetailAdapter.mPosition;

public class CompanyActivity extends BaseActivity {


    @InjectView(R.id.ivBack)
    ImageView ivBack;
    @InjectView(R.id.tvTitle)
    TextView tvTitle;
    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.listview)
    ListView listview;
    @InjectView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @InjectView(R.id.txtDianhua)
    TextView txtDianhua;
    @InjectView(R.id.txtJob)
    TextView txtJob;
    @InjectView(R.id.txtNews)
    TextView txtNews;
    @InjectView(R.id.txtCollect)
    TextView txtCollect;
    @InjectView(R.id.tab_menu)
    LinearLayout tabMenu;
    View views[] = {txtDianhua, txtJob, txtNews, txtCollect};
    @InjectView(R.id.tvCompany)
    TextView tvCompany;
    private String[] strs = {"服务员", "清洁工", "收银", "岛国", "数码", "电脑办公",
            "个护化妆", "图书", "图书", "图书", "图书", "图书", "图书"};
    private ParserTask mParserTask;
    private int positon = 0;

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.txtDianhua:
                positon = 0;
                break;
            case R.id.txtJob:
                positon = 1;
                break;
            case R.id.txtNews:
                positon = 2;
                break;
            case R.id.txtCollect:
                positon = 3;
                break;
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvCompany:
                CompanyInfoDetailFragment companyInfoDetailFragment = new CompanyInfoDetailFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, companyInfoDetailFragment);
//                Bundle bundle = new Bundle();
////                bundle.putString(companyInfoDetailFragment.TAG, strs[position]);
//                companyInfoDetailFragment.setArguments(bundle);
                fragmentTransaction.commit();
                break;
        }


    }

    @Override
    public void initView() {

        ivBack.setOnClickListener(this);
        tvTitle.setText("公司信息");
        txtDianhua.setOnClickListener(this);
        txtJob.setOnClickListener(this);
        txtNews.setOnClickListener(this);
        tabMenu.setOnClickListener(this);
        tvCompany.setOnClickListener(this);
    }

    @Override
    public void initData() {
        CompanyInfoDetailAdapter infoDetailAdapter = new CompanyInfoDetailAdapter(this, strs);
        listview.setAdapter(infoDetailAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                //拿到当前位置
                mPosition = position;
                //即使刷新adapter
                CompanyInfoDetailFragment companyInfoDetailFragment = new CompanyInfoDetailFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, companyInfoDetailFragment);
                Bundle bundle = new Bundle();
                bundle.putString(companyInfoDetailFragment.TAG, strs[position]);
                companyInfoDetailFragment.setArguments(bundle);
                fragmentTransaction.commit();
            }
        });

        //创建MyFragment对象
        CompanyInfoDetailFragment companyInfoDetailFragment = new CompanyInfoDetailFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, companyInfoDetailFragment);
        //通过bundle传值给MyFragment
        Bundle bundle = new Bundle();
        bundle.putString(CompanyInfoDetailFragment.TAG, strs[mPosition]);
        companyInfoDetailFragment.setArguments(bundle);
        fragmentTransaction.commit();

        ApiHttpClient.postBanner("", mHandler);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_company;
    }

    protected AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBytes) {

            executeParserTask(responseBytes);

        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    class ParserTask extends AsyncTask<Void, Void, String> {

        private final byte[] reponseData;
        private boolean parserError = false;
        private List<NewsResult.NewsBean> list;
        private int status;
        private BannerInfo result;

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
                    result = JSON.parseObject(is, BannerInfo.class);
                    if (status == 403)
                        return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//
            try {
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                parserError = true;
            }

            return null;
        }

        @Override
        protected void onPostExecute(String resultt) {
            super.onPostExecute(resultt);
//            解析出错
            if (parserError) {

            } else {
//                executeOnLoadDataSuccess(list);
                List<BannerInfo.DataBean> data = result.getData();
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    strings.add(data.get(i).getImagePath());
                }
                if (banner != null) {
                    UIHelper.initBaners(strings, banner);
                }

            }

        }
    }
}
