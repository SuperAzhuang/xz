package com.xiaozhao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xiaozhao.R;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.bean.SimpleBackPage;
import com.xiaozhao.utils.UIHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/5/25.
 */
public class MineJianliFragment extends BaseFragment {


    @InjectView(R.id.lljibenxinxi)
    LinearLayout lljibenxinxi;
    @InjectView(R.id.llqiuzhiyixiang)
    LinearLayout llqiuzhiyixiang;
    @InjectView(R.id.lljiaoyubeijing)
    LinearLayout lljiaoyubeijing;
    @InjectView(R.id.llgongzuojingli)
    LinearLayout llgongzuojingli;
    @InjectView(R.id.llzhuangyejineng)
    LinearLayout llzhuangyejineng;
    @InjectView(R.id.llPingjia)
    LinearLayout llPingjia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_jianli, null);
        //得到数据
        ButterKnife.inject(this, view);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lljibenxinxi:
                UIHelper.showSimpleBack(getSimpleBackActivity(), SimpleBackPage.JIBENXINXI, null);

                break;
            case R.id.llqiuzhiyixiang:
                UIHelper.showSimpleBack(getSimpleBackActivity(), SimpleBackPage.QIUZHIYIXIANG, null);
                break;
            case R.id.lljiaoyubeijing:
                UIHelper.showSimpleBack(getSimpleBackActivity(), SimpleBackPage.JYSHENGCHENGYE, null);
                break;
            case R.id.llgongzuojingli:
                UIHelper.showSimpleBack(getSimpleBackActivity(), SimpleBackPage.JINGLISHENGCHENGYE, null);
                break;
            case R.id.llzhuangyejineng:
                UIHelper.showSimpleBack(getSimpleBackActivity(), SimpleBackPage.ZHUANYEJINENG, null);
                break;
            case R.id.llPingjia:
                UIHelper.showSimpleBack(getSimpleBackActivity(), SimpleBackPage.PINGJIA, null);
                break;
        }

    }

    @Override
    public void initView(View view) {
        lljibenxinxi.setOnClickListener(this);
        llqiuzhiyixiang.setOnClickListener(this);
        lljiaoyubeijing.setOnClickListener(this);
        llgongzuojingli.setOnClickListener(this);
        llzhuangyejineng.setOnClickListener(this);
        llPingjia.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }
}
