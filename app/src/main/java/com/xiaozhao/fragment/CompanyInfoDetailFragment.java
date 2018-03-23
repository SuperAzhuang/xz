package com.xiaozhao.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaozhao.R;
import com.xiaozhao.base.BaseFragment;

/**
 * Created by Administrator on 2018/3/23.
 */

public class CompanyInfoDetailFragment extends BaseFragment {

    public static final String TAG = "CompanyInfoDetailFragment";
    private String str;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.company_info, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        //得到数据
        str = getArguments().getString(TAG);
        tv_title.setText(str);
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