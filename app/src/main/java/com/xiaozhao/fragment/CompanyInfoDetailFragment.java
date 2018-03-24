package com.xiaozhao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.xiaozhao.R;
import com.xiaozhao.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/3/23.
 */

public class CompanyInfoDetailFragment extends BaseFragment {

    public static final String TAG = "CompanyInfoDetailFragment";
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tvRenshu)
    TextView tvRenshu;
    @InjectView(R.id.tvChangye)
    TextView tvChangye;
    @InjectView(R.id.ll)
    LinearLayout ll;
    @InjectView(R.id.expandable_text)
    TextView expandable_text;
    private String str;
    private String strs = "\"福建省智晶彩网络科技有限公司于2017年03月14日成立。法定代表人曾鹏展,公司经营范围包括：网络技术的研发；软件设计与开发；游戏开发；系统集成服务；网站设计与开发；网页制作；通信系统开发集成、软件销售、电信服务（不含需前置许可的项目）等福建省智晶彩网络科技有限公司于2017年03月14日成立。法定代表人曾鹏展,公司经营范围包括：网络技术的研发；软件设计与开发；游戏开发；系统集成服务；网站设计与开发；网页制作；通信系统开发集成、软件销售、电信服务（不含需前置许可的项目）等\"";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.company_info, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        //得到数据
        if (getArguments() != null) {
            str = getArguments().getString(TAG);
        } else {
            str = "福建省智晶彩网络科技有限公司";
        }
        tv_title.setText(str);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView(View view) {
        expandable_text.setText(strs);
    }

    @Override
    public void initData() {

    }
}