package com.xiaozhao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.xiaozhao.R;
import com.xiaozhao.base.BaseFragment;
import com.xiaozhao.bean.SimpleBackPage;
import com.xiaozhao.utils.UIHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/5/25.
 */
public class PingJiaFragment extends BaseFragment {


    @InjectView(R.id.etPingjia)
    EditText etPingjia;
    @InjectView(R.id.btSave)
    Button btSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_pingjia, null);
        //得到数据
        ButterKnife.inject(this, view);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btSave:
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
