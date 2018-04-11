package com.liweisheng.async;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.liweisheng.constant.ConstantData;
import com.liweisheng.util.HttpUtils;

import java.net.URL;

/**
 * Created by 李维升 on 2018/3/18.
 */

public class LoginThread extends Thread {
    private Context context;
    private String url;
    private Handler handler;

    public LoginThread(Context context, String url, Handler handler) {
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
            message.what = ConstantData.MSG_LOGIN;
            bundle.putString("LOGIN_DATA", data);
            message.setData(bundle);
            handler.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
