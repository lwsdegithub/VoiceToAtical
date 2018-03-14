package com.liweisheng.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.liweisheng.R;
import com.liweisheng.async.LoginAsyncTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by 李维升 on 2018/3/13.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginAsyncTask.GetHashMapListener {
    private Button button;
    private LoginAsyncTask loginAsyncTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = this.findViewById(R.id.async_test);
        button.setOnClickListener(this);
        loginAsyncTask = new LoginAsyncTask(this, this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.async_test) {
            try {
                loginAsyncTask.execute(new URL("http://blog.csdn.net/iispring/article/details/50639090"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getHashMap(HashMap<String, String> dataMap) {
        Toast.makeText(this, dataMap.get("data"), Toast.LENGTH_LONG).show();
        if (loginAsyncTask != null && loginAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            loginAsyncTask.cancel(true);
        }
    }
}
