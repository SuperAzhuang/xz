package com.xiaozhao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaozhao.base.BaseActivity;
import com.xiaozhao.utils.UIHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;



public class RegistActivity extends BaseActivity {


    @InjectView(R.id.et_account)
    EditText etAccount;
    @InjectView(R.id.iv_delete)
    ImageView ivDelete;
    @InjectView(R.id.etCheckCode)
    EditText etCheckCode;
    @InjectView(R.id.etPssword)
    EditText etPssword;
    @InjectView(R.id.etagainPssword)
    EditText etagainPssword;
    @InjectView(R.id.checkbox)
    CheckBox checkbox;
    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.btregist)
    Button btregist;
    @InjectView(R.id.activity_regist)
    LinearLayout activityRegist;
//    private TLSService tlsService;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btregist:
                UIHelper.showMainActivity(this);
                finish();
                break;

        }
    }

    @Override
    public void initView() {

//        tlsService = TLSService.getInstance();
//        tlsService.initSmsRegisterService(this, etCountryCode, etAccount, etCheckCode, btn_requireCheckCode_hostRegister, btregist);
        btregist.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_regist;
    }

}
