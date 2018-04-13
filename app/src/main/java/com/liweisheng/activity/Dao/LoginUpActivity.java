package com.liweisheng.activity.Dao;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.liweisheng.R;
import com.liweisheng.async.LoginUpThread;
import com.liweisheng.base.BaseActivity;
import com.liweisheng.constant.ConstantData;
import com.liweisheng.constant.ResponseCode;

/**
 * Created by 李维升 on 2018/3/14.
 */

public class LoginUpActivity extends BaseActivity implements View.OnClickListener {
    private LoginUpThread loginUpThread;
    private TextInputEditText tiePhone;
    private TextInputEditText tiePwd;
    private TextInputEditText tiePwdConfirm;
    private Button btnLoginIn;
    private Button btnLoginCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_up);
        this.initView();
    }

    private void initView() {
        tiePhone = findViewById(R.id.tie_phone);
        tiePwd = findViewById(R.id.tie_pwd);
        tiePwdConfirm = findViewById(R.id.tie_pwd_confirm);
        btnLoginIn = findViewById(R.id.btn_login_up);
        btnLoginCancel = findViewById(R.id.btn_login_up_cancel);
        btnLoginCancel.setOnClickListener(this);
        btnLoginIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_up:
                String user = tiePhone.getText().toString();
                String pwd = tiePwd.getText().toString();
                String pwdConfirm = tiePwdConfirm.getText().toString();
                if (user.isEmpty() || pwd.isEmpty() || pwdConfirm.isEmpty()) {
                    Toast.makeText(this, "手机号或密码不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    loginUpThread = new LoginUpThread(this, ConstantData.BASE_URL + "LoginUpServlet?phone=" + user + "&" + "password=" + pwd + "&" + "passwordConfirm=" + pwdConfirm, handler);
                    loginUpThread.start();
                }
                break;
            case R.id.btn_login_up_cancel:
                this.finish();
                break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == ConstantData.MSG_LOGIN_UP) {
                String data = msg.getData().getString("LOGIN_UP_DATA");
                if (data.equals(ResponseCode.SERVER_ERROR)) {
                    Toast.makeText(getApplicationContext(), "服务器出错", Toast.LENGTH_LONG).show();
                } else {
                    switch (data) {
                        case ResponseCode.ACCOUNT_IS_EXIST:
                            Toast.makeText(LoginUpActivity.this, "账户已经存在，如忘记密码请找回", Toast.LENGTH_SHORT).show();
                            break;
                        case ResponseCode.PWD_NOT_SAME:
                            Toast.makeText(LoginUpActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                            break;
                        case ResponseCode.DB_ERROR:
                            Toast.makeText(LoginUpActivity.this, "服务器数据库出现错误", Toast.LENGTH_SHORT).show();
                            break;
                        case ResponseCode.LOGIN_UP_SUCCESS:
                            //登陆成功
                            break;
                        case ResponseCode.NOT_PHONE_NUMBER:
                            Toast.makeText(LoginUpActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        }
    };
}
