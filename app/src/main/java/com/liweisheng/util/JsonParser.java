package com.liweisheng.util;

import com.google.gson.Gson;
import com.liweisheng.bean.RecognizerResult;

import java.util.List;

/**
 * Created by 李维升 on 2017/12/21.
 */

public class JsonParser {
    //解析语音听写时的Json字符串
    public static String parserRecognizerResult(String json){
        RecognizerResult recognizerResult;
        Gson gson=new Gson();
        StringBuffer stringBuffer=new StringBuffer();
        recognizerResult=gson.fromJson(json,RecognizerResult.class);
        List<RecognizerResult.ws> wsList=recognizerResult.getWs();
        for (RecognizerResult.ws ws : wsList){
            List<RecognizerResult.ws.cw> cwList=ws.getCw();
            stringBuffer.append(cwList.get(0).getW());
        }
        return stringBuffer.toString().trim();
    }
}
