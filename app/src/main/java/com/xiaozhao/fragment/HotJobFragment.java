package com.xiaozhao.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import com.xiaozhao.adapter.HotJobAdapter;
import com.xiaozhao.manager.DividerItemDecoration ;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xiaozhao.R;
import com.xiaozhao.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.R.attr.delay;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotJobFragment extends BaseFragment {

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_job, container, false);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onClick(View view) {


    }

    @Override
    public void initView(View view) {
        // mannger管理器
        LinearLayoutManager mLayoutManager = (LinearLayoutManager) new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getApplication()));

    }

    @Override
    public void initData() {

    }


}
