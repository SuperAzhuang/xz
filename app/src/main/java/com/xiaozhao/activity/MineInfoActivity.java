package com.xiaozhao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaozhao.R;
import com.xiaozhao.base.BaseActivity;
import butterknife.InjectView;

public class MineInfoActivity extends BaseActivity {

    @InjectView(R.id.tvLoginRegist)
    TextView tvLoginRegist;
    @InjectView(R.id.ivHeader)
    ImageView ivHeader;
    @InjectView(R.id.ivBack)
    ImageView ivBack;
    @InjectView(R.id.etNicheng)
    EditText etNicheng;
    @InjectView(R.id.etName)
    EditText etName;
    @InjectView(R.id.tvSex)
    TextView tvSex;
    @InjectView(R.id.tvNianling)
    TextView tvNianling;
    @InjectView(R.id.llqiye)
    LinearLayout llqiye;
    @InjectView(R.id.etPhone)
    EditText etPhone;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
        }
    }

    @Override
    public void initView() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mime_info;
    }

}
