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
import com.xiaozhao.adapter.MineFabuQiuZhiAdapter;
import com.xiaozhao.adapter.MineZhaoPingAdapter;
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

public class JiaoyuShengChengYeFragment extends BaseFragment {

    @InjectView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @InjectView(R.id.btAdd)
    Button btAdd;
    private ArrayList mDatas = new ArrayList();
    private JiaoyuShengChengYeAdapter mAdapter;
    boolean isEdit;
    private NewsResult.NewsBean newsBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jiaoyu_shengchengye, null);
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

        mAdapter = new JiaoyuShengChengYeAdapter(R.layout.item_jiaoyu_shengchengye, mDatas, getActivity());
        mRecyclerView.setAdapter(mAdapter);
// 开启滑动删除
        mAdapter.enableSwipeItem();
        mAdapter.setOnItemSwipeListener(onItemSwipeListener);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                UIHelper.showSimpleBack(getSimpleBackActivity(), SimpleBackPage.JIAOYUBEIJING, null);
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
    OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
        @Override
        public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {

        }
        @Override
        public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

        }
        @Override
        public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
            mAdapter.remove(pos);
        }

        @Override
        public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

        }
    };

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
                UIHelper.showSimpleBack(getSimpleBackActivity(), SimpleBackPage.JIAOYUBEIJING, null);
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
