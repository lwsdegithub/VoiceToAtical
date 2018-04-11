package com.liweisheng.async;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.liweisheng.constant.ConstantData;
import com.liweisheng.util.HttpUtils;

import java.net.URL;

/**
 * Created by 李维升 on 2018/4/10.
 */

public class LoginUpThread extends Thread {
    private Context context;
    private String url;
    private Handler handler;

    public LoginUpThread(Context context, String url, Handler handler) {
        this.context = context;
        this.url = url;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            Message message = new Message();
            Bundle bundle = new Bundle();
            URL urlEntity = new URL(url);
            String data = HttpUtils.getHttpData(urlEntity);
            message.what = ConstantData.MSG_LOGIN_UP;
            bundle.putString("LOGIN_UP_DATA", data);
            message.setData(bundle);
            handler.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
