package com.xiaozhao.activity;

import android.os.Bundle;
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
import com.xiaozhao.utils.UIHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.xiaozhao.R.id.et_account;
import static com.xiaozhao.R.id.iv;


public class RegistActivity extends BaseActivity {


    @InjectView(R.id.ivBack)
    ImageView ivBack;
    @InjectView(et_account)
    EditText etAccount;
    @InjectView(R.id.ivPhonedelete)
    ImageView ivPhonedelete;
    @InjectView(R.id.etCheckCode)
    EditText etCheckCode;
    @InjectView(R.id.ivGetCode)
    ImageView ivGetCode;
    @InjectView(R.id.etPssword)
    EditText etPssword;
    @InjectView(R.id.ivPssworDelete)
    ImageView ivPssworDelete;
    @InjectView(R.id.etagainPssword)
    EditText etagainPssword;
    @InjectView(R.id.etAgainPssworDelete)
    ImageView etAgainPssworDelete;
    @InjectView(R.id.checkbox)
    CheckBox checkbox;
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
//                    注册
                }
                UIHelper.showLoginActivity(this);
                finish();
                break;

            case R.id.ivBack:
                finish();
                break;
            case R.id.ivGetCode:
//                获取验证码

                break;
            case R.id.ivPhonedelete:
                etAccount.setText("");
                break;
            case R.id.ivPssworDelete:
                etPssword.setText("");
                break;
            case R.id.etAgainPssworDelete:
                etagainPssword.setText("");
                break;


        }
    }

    @Override
    public void initView() {

        btregist.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivPhonedelete.setOnClickListener(this);
        ivGetCode.setOnClickListener(this);
        ivPssworDelete.setOnClickListener(this);
        etAgainPssworDelete.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_regist;
    }

}
