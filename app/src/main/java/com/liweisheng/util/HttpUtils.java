package com.liweisheng.util;

import com.liweisheng.constant.ResponseCode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by 李维升 on 2018/3/12.
 */

public class HttpUtils {
    public static String getHttpData(URL url) {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        String data = ResponseCode.SERVER_ERROR;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset", "utf-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream();
            int temp;
            //缓存区
            byte[] bytes = new byte[1024];
            while ((temp = inputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, temp);
            }
            data = new String(byteArrayOutputStream.toByteArray(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
