package com.xiaozhao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xiaozhao.R;
import com.xiaozhao.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/5/25.
 */
public class ZhiWeiMiaoShuFragment extends BaseFragment {


    @InjectView(R.id.etPingjia)
    EditText etPingjia;
    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.btSave)
    Button btSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhiweimiaoshu_fragment, null);
        //得到数据
        ButterKnife.inject(this, view);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btSave:

                getSimpleBackActivity().hideKeyboard(etPingjia.getWindowToken());
                getSimpleBackActivity().finish();

                break;
        }

    }

    @Override
    public void initView(View view) {
        btSave.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

}
