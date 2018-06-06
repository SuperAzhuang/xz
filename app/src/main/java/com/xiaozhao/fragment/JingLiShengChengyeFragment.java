package com.xiaozhao.fragment;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.xiaozhao.R;
import com.xiaozhao.adapter.JiaoyuShengChengYeAdapter;
import com.xiaozhao.adapter.JingLiShengChengYeAdapter;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.bean.NewsResult;
import com.xiaozhao.bean.SimpleBackPage;
import com.xiaozhao.manager.DividerItemDecoration;
import com.xiaozhao.utils.UIHelper;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by Administrator on 2018/5/30.
 */

public class JingLiShengChengyeFragment extends BaseFragment {

    @InjectView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @InjectView(R.id.btAdd)
    Button btAdd;
    private ArrayList mDatas = new ArrayList();
    private JingLiShengChengYeAdapter mAdapter;
    boolean isEdit;
    private NewsResult.NewsBean newsBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jingli_shengchengye, null);
        //得到数据
        ButterKnife.inject(this, view);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplication()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsBean = new NewsResult.NewsBean();

        newsBean.setEdit(isEdit);
        mDatas.add(newsBean);
        mDatas.add(newsBean);
        mDatas.add(newsBean);
        mDatas.add(newsBean);

        mAdapter = new JingLiShengChengYeAdapter(R.layout.item_jingli_shengchengye, mDatas, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                UIHelper.showSimpleBack(getSimpleBackActivity(), SimpleBackPage.GONGZUOJINGLI, null);
            }
        });
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (view.getId()) {
                    case R.id.ivDelete:
                        mAdapter.remove(i);
                        break;
                }
            }
        });


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSave:
                getSimpleBackActivity().tvSave.setText(!isEdit ? "完成" : "编辑");
                isEdit = !isEdit;
                newsBean.setEdit(isEdit);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.btAdd:
                UIHelper.showSimpleBack(getSimpleBackActivity(), SimpleBackPage.GONGZUOJINGLI, null);
                break;
        }
    }

    @Override
    public void initView(View view) {
        getSimpleBackActivity().tvSave.setText("编辑");
        getSimpleBackActivity().tvSave.setVisibility(View.VISIBLE);
        getSimpleBackActivity().tvSave.setOnClickListener(this);
        btAdd.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }
}
