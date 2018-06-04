package com.xiaozhao.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xiaozhao.R;
import com.xiaozhao.adapter.MineZhaoPingAdapter;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.bean.NewsResult;
import com.xiaozhao.bean.SimpleBackPage;
import com.xiaozhao.manager.DividerItemDecoration;
import com.xiaozhao.utils.UIHelper;
import com.xiaozhao.view.EmptyLayout;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/5/25.
 */

public class MineZhaopingFragment extends BaseFragment {

    @InjectView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @InjectView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @InjectView(R.id.error_layout)
    EmptyLayout errorLayout;
    private ArrayList mDatas = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mine_zhaoping_fragment, container, false);
        ButterKnife.inject(this, view);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplication()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDatas.add(new NewsResult.NewsBean());
        mDatas.add(new NewsResult.NewsBean());

        MineZhaoPingAdapter mAdapter = new MineZhaoPingAdapter(R.layout.item_minezhaoping, mDatas, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {

            }
        });

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
