package com.xiaozhao.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaozhao.R;
import com.xiaozhao.base.BaseActivity;
import butterknife.InjectView;



public class RegistActivity extends BaseActivity  {


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
    @InjectView(R.id.ivGetCode)
    ImageView ivGetCode;
    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.btregist)
    Button btregist;
    @InjectView(R.id.activity_regist)
    LinearLayout activityRegist;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btregist:

                String pwd = etPssword.getText().toString();
                String pwd_confirm = etagainPssword.getText().toString();
                if (!TextUtils.isEmpty(pwd) && pwd.equals(pwd_confirm)) {
//            dialog.dismiss();
                }
                break;

        }
    }

    @Override
    public void initView() {

//        tlsService = TLSService.getInstance();
//        tlsService.initSmsRegisterService(this, etCountryCode, etAccount, etCheckCode, btn_requireCheckCode_hostRegister, btregist);
        btregist.setOnClickListener(this);


        btregist.setOnClickListener(this);

        ivGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etAccount.getText().toString();
                if (!TextUtils.isEmpty(phone)) {
//                    getIdentifierCode.setVisibility(View.GONE);
                } else {
                    Toast.makeText(RegistActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_regist;
    }

}
