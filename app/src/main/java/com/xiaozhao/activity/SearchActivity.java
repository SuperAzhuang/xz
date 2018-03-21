package com.xiaozhao.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fyales.tagcloud.library.TagBaseAdapter;
import com.fyales.tagcloud.library.TagCloudLayout;
import com.xiaozhao.R;
import com.xiaozhao.base.BaseActivity;
import com.xiaozhao.base.BaseApplication;
import com.xiaozhao.utils.UIHelper;

import java.util.ArrayList;

import butterknife.InjectView;


public class SearchActivity extends BaseActivity {


    @InjectView(R.id.ll_detailedgoback)
    LinearLayout llDetailedgoback;
    @InjectView(R.id.et_search)
    EditText etSearch;
    @InjectView(R.id.iv_delete)
    LinearLayout ivDelete;
    @InjectView(R.id.ll_typeselect)
    LinearLayout llTypeselect;
    @InjectView(R.id.tv_searchinfo)
    TextView tvSearchinfo;
    @InjectView(R.id.ll_actionbar)
    LinearLayout llActionbar;
    @InjectView(R.id.tabCloudLayout)
    TagCloudLayout mTabCloudLayout;
    @InjectView(R.id.rv_search_nearly)
    RecyclerView rvSearchNearly;
    @InjectView(R.id.bt_clearing)
    Button btClearing;
    @InjectView(R.id.ll_search_nearly)
    LinearLayout llSearchNearly;
    @InjectView(R.id.ll_invisible)
    LinearLayout llInvisible;
    @InjectView(R.id.rv_search_about)
    RecyclerView rvSearchAbout;
    @InjectView(R.id.ll_search_now)
    LinearLayout llSearchNow;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_detailedgoback:
                finish();
                break;
            case R.id.tvLocate:

                break;
            case R.id.ivScanner:

                break;
            case R.id.ivSearch:
                UIHelper.showSearchActivity(this);
                break;

        }
    }

    @Override
    public void initView() {
        llDetailedgoback.setOnClickListener(this);

    }

    @Override
    public void initData() {
     final ArrayList<String> mList = new ArrayList<>();
        mList.add("IT");
        mList.add("电话销售");
        mList.add("技术工程师");
        mList.add("电路设计");
        mList.add("UI工程师");
        mList.add("经理");
        mList.add("清洁工");
        mList.add("服务员");
        mList.add("厨师");
        TagBaseAdapter mAdapter = new TagBaseAdapter(this,mList);
//        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mList.add("东帝汶");
//                mAdapter.notifyDataSetChanged();
//            }
//        });
        mTabCloudLayout.setAdapter(mAdapter);
        mTabCloudLayout.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                BaseApplication.showToastShort(mList.get(position));
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

}
