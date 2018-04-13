package com.liweisheng.activity.Dao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.liweisheng.R;
import com.liweisheng.activity.MainActivity;
import com.liweisheng.async.LoginThread;
import com.liweisheng.base.BaseActivity;
import com.liweisheng.constant.ConstantData;
import com.liweisheng.constant.ResponseCode;


/**
 * Created by 李维升 on 2018/3/13.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private LoginThread loginThread;
    private TextInputEditText tiePhone;
    private TextInputEditText tiePwd;
    private Button btnLoginIn;
    private Button btnLoginCancel;
    private TextView tvNotUser;
    private TextView tvForgetPwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.initView();
    }

    private void initView() {
        tiePhone = findViewById(R.id.tie_phone);
        tiePwd = findViewById(R.id.tie_pwd);
        btnLoginIn = findViewById(R.id.btn_login_up);
        btnLoginCancel = findViewById(R.id.btn_login_up_cancel);
        tvNotUser = findViewById(R.id.tv_not_user);
        tvForgetPwd = findViewById(R.id.tv_forget_pwd);
        btnLoginIn.setOnClickListener(this);
        btnLoginCancel.setOnClickListener(this);
        tvNotUser.setOnClickListener(this);
        tvForgetPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_up:
                String user = tiePhone.getText().toString();
                String pwd = tiePwd.getText().toString();
                if (user.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(this, "手机号或密码不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    loginThread = new LoginThread(this, ConstantData.BASE_URL + "LoginServlet?phone=" + user + "&" + "password=" + pwd, handler);
                    loginThread.start();
                }
                break;
            case R.id.btn_login_up_cancel:
                this.finish();
                break;
            case R.id.tv_not_user:
                startActivity(new Intent(LoginActivity.this, LoginUpActivity.class));
                tiePhone.getText().clear();
                tiePwd.getText().clear();
                break;
            case R.id.tv_forget_pwd:
                startActivity(new Intent(LoginActivity.this, ForgetPwdActivity.class));
                break;
        }
    }

    //更新UI
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == ConstantData.MSG_LOGIN) {
                String data = msg.getData().getString("LOGIN_DATA");
                if (data.equals(ResponseCode.SERVER_ERROR)) {
                    Toast.makeText(getApplicationContext(), "服务器出错", Toast.LENGTH_LONG).show();
                } else {
                    if (data.equals(ResponseCode.LOGIN_SUCCESS)) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        LoginActivity.this.finish();
                    }
                    if (data.equals(ResponseCode.PWD_NOT_CORRECTED)) {
                        Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_LONG).show();
                    }
                    if (data.equals(ResponseCode.ACCOUNT_NOT_EXIST)) {
                        Toast.makeText(getApplicationContext(), "账户不存在，请检查手机号", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    };
}
