package com.xiaozhao.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xiaozhao.R;
import com.xiaozhao.adapter.MineZhaoPingAdapter;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.bean.NewsResult;
import com.xiaozhao.manager.DividerItemDecoration;
import com.xiaozhao.view.EmptyLayout;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.xiaozhao.R.id.mRecyclerView;

/**
 * Created by Administrator on 2018/5/25.
 */

public class HuiYuanFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.huiyuan_fragment, container, false);
        ButterKnife.inject(this, view);



        return view;
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }
}
