package com.xiaozhao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaozhao.R;
import com.xiaozhao.activity.MineInfoActivity;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.bean.SimpleBackPage;
import com.xiaozhao.utils.UIHelper;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SettingsFragment  extends BaseFragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);
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
