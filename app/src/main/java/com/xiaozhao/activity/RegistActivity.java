package com.xiaozhao.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.xiaozhao.R;
import com.xiaozhao.base.BaseActivity;
import com.xiaozhao.base.BaseApplication;
import com.xiaozhao.bean.GetCheckCodeResult;
import com.xiaozhao.bean.NewsResult;
import com.xiaozhao.http.Url;
import com.xiaozhao.http.XiaoZhaoHttpApi;
import com.xiaozhao.utils.TDevice;
import com.xiaozhao.utils.UIHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

import static com.xiaozhao.R.id.et_account;


public class RegistActivity extends BaseActivity {


    @InjectView(R.id.ivBack)
    ImageView ivBack;
    @InjectView(R.id.et_account)
    EditText etAccount;
    @InjectView(R.id.ivPhonedelete)
    ImageView ivPhonedelete;
    @InjectView(R.id.etCheckCode)
    EditText etCheckCode;
    @InjectView(R.id.ivGetCode)
    TextView ivGetCode;
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
    private String phone;
    private boolean isGetCheckCodeIng = false;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            int count = msg.what;
            // secondCount--;
            count--;
            if (count == 0) {
                ivGetCode.setText("重发");
                isGetCheckCodeIng = false;
            } else {
                ivGetCode.setText(count + "(s)");
                sendEmptyMessageDelayed(count, 1000);
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btregist:
                getRegister();
                break;

            case R.id.ivBack:
                finish();
                break;
            case R.id.ivGetCode:
//                获取验证码
                getCheckCode();
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

    /**
     * 注册
     */
    private void getRegister() {
        if (!TDevice.hasInternet()) {
            BaseApplication.showToastShort(R.string.error_view_network_error_click_to_refresh);
            return;
        }
        phone = etAccount.getText().toString();
        if (!TDevice.isMobile(phone)) {
            BaseApplication.showToastShort("请输入正确的手机号码");
            return;
        }
        String pwd = etPssword.getText().toString();
        String pwd_confirm = etagainPssword.getText().toString();

        String msgcode = etCheckCode.getText().toString().trim();
        if (TextUtils.isEmpty(msgcode)) {
            BaseApplication.showToastShort("请输入验证码");
            return;
        }

        if (!TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(pwd_confirm) && pwd.equals(pwd_confirm)) {
//                    注册
            XiaoZhaoHttpApi.getRegister(Url.REGISTER, phone, msgcode, pwd_confirm, new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {

                    GetCheckCodeResult result = JSON.parseObject(new String(arg2), GetCheckCodeResult.class);
                    if (result.getStatus() == 1) {
//                    isGetCheckCodeIng = true;
                        BaseApplication.showToastShort(result.getMsg());
                        UIHelper.showLoginActivity(RegistActivity.this);
                        finish();
                    } else {
                        BaseApplication.showToastShort(result.getMsg());
                    }
                }

                @Override
                public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
                    BaseApplication.showToastShort("注册失败");
                }
            });
        } else {
            BaseApplication.showToastShort("两次输入的密码不一致");
        }
    }

    /**
     * 获取验证码接口
     */
    private void getCheckCode() {
        if (isGetCheckCodeIng) {
            return;
        }
        if (!TDevice.hasInternet()) {
            BaseApplication.showToastShort(R.string.error_view_network_error_click_to_refresh);
            return;
        }

        phone = etAccount.getText().toString();
        if (!TDevice.isMobile(phone)) {
            BaseApplication.showToastShort("请输入正确的手机号码");
            return;
        }
        XiaoZhaoHttpApi.getCheckCode(Url.GET_CHECKCODE, phone, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {

                GetCheckCodeResult result = JSON.parseObject(new String(arg2), GetCheckCodeResult.class);
                if (result.getStatus() == 1) {
                    mHandler.sendEmptyMessage(60);
                    isGetCheckCodeIng = true;
                    BaseApplication.showToastShort(result.getMsg());
                } else {
                    BaseApplication.showToastShort(result.getMsg());
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
                BaseApplication.showToastShort("获取验证码失败");
            }
        });
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isGetCheckCodeIng = false;
        mHandler.removeCallbacksAndMessages(null);
    }
}
