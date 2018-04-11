package com.liweisheng.constant;

/**
 * Created by 李维升 on 2018/3/18.
 */

public class ResponseCode {
    //服务器出错
    public static final String SERVER_ERROR = "1111";
    //登陆成功
    public static final String LOGIN_SUCCESS = "0000";
    //密码不正确
    public static final String PWD_NOT_CORRECTED = "0001";
    //账户不存在
    public static final String ACCOUNT_NOT_EXIST = "0002";
    //账户已经存在（注册时使用）
    public static final String ACCOUNT_IS_EXIST = "0003";
    //不是手机号
    public static final String NOT_PHONE_NUMBER = "0004";
    //两次输入密码不相同
    public static final String PWD_NOT_SAME = "0005";
    //注册成功
    public static final String LOGIN_UP_SUCCESS = "0006";
    //注册时数据库出错
    public static final String DB_ERROR = "0007";
}
