package com.liweisheng.com.liweisheng.Util;

import android.content.Context;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;

/**
 * Created by 李维升 on 2017/12/28.
 */

public class FfmpegUtils {
    private String[] cmd;
    private FFmpeg fFmpeg;
    private ExecuteBinaryResponseHandler executeBinaryResponseHandler;
    public FfmpegUtils(Context context,String[] cmd,ExecuteBinaryResponseHandler executeBinaryResponseHandler){
        this.cmd=cmd;
        this.executeBinaryResponseHandler=executeBinaryResponseHandler;
        fFmpeg=FFmpeg.getInstance(context);
    }
    public void execute(){
        try {
            fFmpeg.execute(cmd,executeBinaryResponseHandler);
        }catch (Exception e){

        }

    }

}
