package com.xiaozhao.fragment;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaozhao.R;
import com.xiaozhao.adapter.CompanyInfoDetailAdapter;
import com.xiaozhao.base.BaseFragment;
import com.youth.banner.Banner;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.xiaozhao.adapter.CompanyInfoDetailAdapter.mPosition;

public class CompanyIntroFragment extends BaseFragment {

    @InjectView(R.id.ivBack)
    ImageView ivBack;
    @InjectView(R.id.tvTitle)
    TextView tvTitle;
    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.listview)
    ListView listview;
    @InjectView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    private String[] strs = {"公司介绍", "服务员", "清洁工", "收银", "岛国", "数码", "电脑办公",
            "个护化妆", "图书"};

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
        tvTitle.setText("公司信息");
    }

    @Override
    public void initData() {

        CompanyInfoDetailAdapter infoDetailAdapter = new CompanyInfoDetailAdapter(getActivity(), strs);
        listview.setAdapter(infoDetailAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                //拿到当前位置
                mPosition = position;
                //即使刷新adapter
                CompanyInfoDetailFragment companyInfoDetailFragment = new CompanyInfoDetailFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, companyInfoDetailFragment);
                Bundle bundle = new Bundle();
                bundle.putString(companyInfoDetailFragment.TAG, strs[position]);
                companyInfoDetailFragment.setArguments(bundle);
                fragmentTransaction.commit();
            }
        });

        //创建MyFragment对象
        CompanyInfoDetailFragment companyInfoDetailFragment = new CompanyInfoDetailFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, companyInfoDetailFragment);
        //通过bundle传值给MyFragment
        Bundle bundle = new Bundle();
        bundle.putString(CompanyInfoDetailFragment.TAG, strs[mPosition]);
        companyInfoDetailFragment.setArguments(bundle);
        fragmentTransaction.commit();

    }

}
