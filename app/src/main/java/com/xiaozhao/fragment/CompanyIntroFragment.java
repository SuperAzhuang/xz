package com.xiaozhao.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaozhao.R;
import com.xiaozhao.base.BaseFragment;

import butterknife.ButterKnife;

public class CompanyIntroFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_intro, container, false);
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
