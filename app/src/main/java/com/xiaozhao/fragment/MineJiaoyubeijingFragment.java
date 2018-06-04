package com.xiaozhao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaozhao.R;
import com.xiaozhao.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/30.
 */

public class MineJiaoyubeijingFragment extends BaseFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_jiaoyubeijing, null);
        //得到数据
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView(View view) {
        getSimpleBackActivity().tvSave.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {

    }
}
