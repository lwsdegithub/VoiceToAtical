package com.liweisheng.constant;

/**
 * Created by 李维升 on 2017/12/19.
 */

public class ConstantData {
    //科大讯飞的开发者ID
    public static final String APP_ID_FOR_XF = "5a37e19f";
    //百度语音的相关数据
    public static final String APP_ID_FOR_BD = "10578198";
    public static final String APIKEYFORBAIDU="pDZLwPUC7po9Z9bu7n7RL73r";
    public static final String SECRETKEYFORBAIDU="NOkmpXaArhuDXqFH8s1oXtUB4FZ9kLGg";
    //选择录音文件返回码
    public static final int FILE_SELECT_CODE=0;
    //音频格式
    public static final String[] audioForms=new String[]{".mp3",".wav",".m4a",".pcm",".aac"};

    public static final String BASE_URL = "http://192.168.137.1:8080/AiWorkServer/";

    public static final int MSG_LOGIN = 0;
    public static final int MSG_LOGIN_UP = 1;
}
