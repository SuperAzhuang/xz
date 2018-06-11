package com.xiaozhao.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.xiaozhao.R;
import com.xiaozhao.base.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.xiaozhao.R.id.llLiZhi;
import static com.xiaozhao.R.id.llRuZhi;
import static com.xiaozhao.R.id.tvLiZhi;
import static com.xiaozhao.R.id.tvRuZhi;
import static com.xiaozhao.R.id.tvXingZhi;

/**
 * Created by Administrator on 2018/5/30.
 */

public class PhotoSelectFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photoselect_fragment, null);
        //得到数据
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tvSave:
                getSimpleBackActivity().finish();
                break;
        }
    }

    @Override
    public void initView(View view) {
        getSimpleBackActivity().tvSave.setVisibility(View.VISIBLE);
        getSimpleBackActivity().tvSave.setText("上传");
        getSimpleBackActivity().tvSave.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }
}
