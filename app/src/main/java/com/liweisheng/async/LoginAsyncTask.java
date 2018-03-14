package com.liweisheng.async;

import android.content.Context;
import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by 李维升 on 2018/3/13.
 */

public class LoginAsyncTask extends AsyncTask<URL, Integer, HashMap<String, String>> {
    private Context context;
    private GetHashMapListener getHashMapListener;

    public LoginAsyncTask(Context context, GetHashMapListener getHashMapListener) {
        this.context = context;
        this.getHashMapListener = getHashMapListener;
    }

    @Override
    protected HashMap<String, String> doInBackground(URL... urls) {
        HashMap<String, String> dataMap = new HashMap<>();
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) urls[0].openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream();
            int temp = -1;
            byte[] bytes = new byte[1024];
            while ((temp = inputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, temp);
            }
            String dataJson = new String(byteArrayOutputStream.toByteArray(), "utf-8");
            dataMap.put("data", dataJson);
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
        return dataMap;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        if (isCancelled()) {
            return;
        }
    }

    @Override
    protected void onPostExecute(HashMap<String, String> dataMap) {
        if (isCancelled()) {
            return;
        }
        getHashMapListener.getHashMap(dataMap);
    }

    //回调接口，供LoginActivity使用
    public interface GetHashMapListener {
        void getHashMap(HashMap<String, String> dataMap);
    }
}
