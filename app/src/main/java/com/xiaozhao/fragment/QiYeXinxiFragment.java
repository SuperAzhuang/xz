package com.xiaozhao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaozhao.R;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.bean.SimpleBackPage;
import com.xiaozhao.utils.UIHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.xiaozhao.R.id.llPingjia;
import static com.xiaozhao.R.id.llgongzuojingli;
import static com.xiaozhao.R.id.lljiaoyubeijing;
import static com.xiaozhao.R.id.lljibenxinxi;
import static com.xiaozhao.R.id.llqiuzhiyixiang;
import static com.xiaozhao.R.id.llzhuangyejineng;

/**
 * Created by Administrator on 2018/5/25.
 */
public class QiYeXinxiFragment extends BaseFragment {


    @InjectView(R.id.etNicheng)
    EditText etNicheng;
    @InjectView(R.id.llhangye)
    LinearLayout llhangye;
    @InjectView(R.id.llleixing)
    LinearLayout llleixing;
    @InjectView(R.id.llguimo)
    LinearLayout llguimo;
    @InjectView(R.id.llHuanjin)
    LinearLayout llHuanjin;
    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.llDizhi)
    LinearLayout llDizhi;
    @InjectView(R.id.llJianjie)
    LinearLayout llJianjie;
    @InjectView(R.id.llZhizhao)
    LinearLayout llZhizhao;
    @InjectView(R.id.llFaren)
    LinearLayout llFaren;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.qiyexinxi_fragment, null);
        //得到数据
        ButterKnife.inject(this, view);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case lljibenxinxi:
                UIHelper.showSimpleBack(getSimpleBackActivity(), SimpleBackPage.JIBENXINXI, null);
                break;
        }

    }

    @Override
    public void initView(View view) {
        llhangye.setOnClickListener(this);
        llleixing.setOnClickListener(this);
        llguimo.setOnClickListener(this);
        llHuanjin.setOnClickListener(this);
        llDizhi.setOnClickListener(this);
        llJianjie.setOnClickListener(this);
        llZhizhao.setOnClickListener(this);
        llFaren.setOnClickListener(this);
        getSimpleBackActivity().tvSave.setVisibility(View.VISIBLE);

    }

    @Override
    public void initData() {

    }

}
