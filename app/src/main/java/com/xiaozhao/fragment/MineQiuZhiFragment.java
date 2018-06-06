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
import com.xiaozhao.R;
import com.xiaozhao.adapter.MineQiuZhiAdapter;
import com.xiaozhao.adapter.MineZhaoPingAdapter;
import com.xiaozhao.base.BaseApplication;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.bean.NewsResult;
import com.xiaozhao.manager.DividerItemDecoration;
import com.xiaozhao.view.EmptyLayout;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/5/25.
 */

public class MineQiuZhiFragment extends BaseFragment {


    @InjectView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @InjectView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @InjectView(R.id.error_layout)
    EmptyLayout errorLayout;
    private ArrayList mDatas = new ArrayList();
    private MineQiuZhiAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_zhaoping_fragment, container, false);
        ButterKnife.inject(this, view);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplication()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDatas.add(new NewsResult.NewsBean());
        mDatas.add(new NewsResult.NewsBean());
        mDatas.add(new NewsResult.NewsBean());
        mDatas.add(new NewsResult.NewsBean());
        mDatas.add(new NewsResult.NewsBean());
        mDatas.add(new NewsResult.NewsBean());

        mAdapter = new MineQiuZhiAdapter(R.layout.item_mineqiuzhi, mDatas, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new com.chad.library.adapter.base.listener.OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }
        });
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

                switch (view.getId()) {
                    case R.id.ivDelete:
                        mAdapter.remove(i);
                        BaseApplication.showToastShort("i = " + i);
                        break;
                }
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
