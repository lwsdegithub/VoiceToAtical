package com.liweisheng.com.liweisheng.Util;

import com.google.gson.Gson;
import com.liweisheng.Bean.RecognizerResult;

/**
 * Created by 李维升 on 2017/12/21.
 */

public class JsonParser {
    //解析语音听写时的Json字符串
    public static String parserRecognizerResult(String json){
        RecognizerResult recognizerResult=new RecognizerResult();
        Gson gson=new Gson();
        StringBuffer stringBuffer=new StringBuffer();
        recognizerResult=gson.fromJson(json,RecognizerResult.class);
        for (int i=1;i<=recognizerResult.getWsList().size();i++){
            stringBuffer.append(recognizerResult.getWsList().get(i).getCw().getW());
        }
        return stringBuffer.toString().trim();
    }
}
