package com.xiaozhao.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaozhao.R;
import com.xiaozhao.base.BaseActivity;
import com.xiaozhao.utils.UIHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class LoginActivity extends BaseActivity {


    @InjectView(R.id.et_account)
    EditText etAccount;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.tvForget)
    TextView tvForget;
    @InjectView(R.id.btLogin)
    Button btLogin;
    @InjectView(R.id.tvRegist)
    TextView tvRegist;
    @InjectView(R.id.tvThird)
    TextView tvThird;
    @InjectView(R.id.ll)
    LinearLayout ll;
    private static final String TAG = "LoginActivity";
    private AlertDialog dialog;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btLogin:
                UIHelper.showMainActivity(this);
                finish();
                break;
            case R.id.tvRegist:
                UIHelper.showRegistActivity(this);
                finish();
                break;
            case R.id.tvForget:
//                UIHelper.showRegistActivity(this);
//                finish();
                break;
        }

    }

    @Override
    public void initView() {

        btLogin.setOnClickListener(this);
        tvRegist.setOnClickListener(this);
        tvForget.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


}
