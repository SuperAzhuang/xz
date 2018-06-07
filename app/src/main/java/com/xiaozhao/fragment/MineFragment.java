package com.xiaozhao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaozhao.R;
import com.xiaozhao.activity.MineInfoActivity;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.bean.SimpleBackPage;
import com.xiaozhao.utils.UIHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/1/26.
 */

public class MineFragment extends BaseFragment {

    @InjectView(R.id.ivHeader)
    ImageView ivHeader;
    @InjectView(R.id.tvLoginRegist)
    TextView tvLoginRegist;
    @InjectView(R.id.llGuangzhu)
    LinearLayout llGuangzhu;
    @InjectView(R.id.llFabu)
    LinearLayout llFabu;
    @InjectView(R.id.llJianli)
    LinearLayout llJianli;
    @InjectView(R.id.llqiye)
    LinearLayout llqiye;
    @InjectView(R.id.llhuiyuan)
    LinearLayout llhuiyuan;
    @InjectView(R.id.tvSetting)
    TextView tvSetting;
    @InjectView(R.id.llZhiweiGuanli)
    LinearLayout llZhiweiGuanli;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mine_fragment, container, false);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivHeader:
                startActivity(new Intent(getMAinActivity(), MineInfoActivity.class));
                break;
            case R.id.tvSetting:
                UIHelper.showSimpleBack(getMAinActivity(), SimpleBackPage.SETTINGS, null);
                break;
            case R.id.llJianli:
                UIHelper.showSimpleBack(getMAinActivity(), SimpleBackPage.JIANLI, null);
                break;
            case R.id.llGuangzhu:
                UIHelper.showSimpleBack(getMAinActivity(), SimpleBackPage.GUANGZHU, null);
                break;
            case R.id.llFabu:
//                UIHelper.showSimpleBack(getMAinActivity(), SimpleBackPage.FABU, null);
                UIHelper.showSimpleBack(getMAinActivity(), SimpleBackPage.FABU, null);
                break;
            case R.id.llqiye:
                UIHelper.showSimpleBack(getMAinActivity(), SimpleBackPage.QIYEXINXI, null);
                break;
            case R.id.llhuiyuan:
                UIHelper.showSimpleBack(getMAinActivity(), SimpleBackPage.HUIYUAN, null);
                break;
            case R.id.llZhiweiGuanli:
                UIHelper.showSimpleBack(getMAinActivity(), SimpleBackPage.ZHIWEIGUANLI, null);
                break;
        }

    }

    @Override
    public void initView(View view) {
        ivHeader.setOnClickListener(this);
        tvSetting.setOnClickListener(this);
        llqiye.setOnClickListener(this);
        llJianli.setOnClickListener(this);
        llGuangzhu.setOnClickListener(this);
        llFabu.setOnClickListener(this);
        llhuiyuan.setOnClickListener(this);
        llZhiweiGuanli.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }


}
