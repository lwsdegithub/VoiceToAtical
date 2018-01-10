package com.liweisheng.com.liweisheng.Util;

import com.liweisheng.EditNoteActivity.EditNoteMainActivityBasedXunFei;

/**
 * Created by 李维升 on 2018/1/9.
 * 处理字符串的工具类
 */

public class StringFactory {
    //转换格式路径
    public static String getNewPath(String oldPath){
        String newPath=null;
        if (oldPath.endsWith(".wav")){
            newPath=oldPath;
        }else if (oldPath.endsWith(".mp3")|oldPath.endsWith(".m4a")|oldPath.endsWith(".pcm")|oldPath.endsWith(".aac")){
            newPath=oldPath.substring(0,oldPath.length()-4)+".wav";
        }
        return newPath;
    }
    //获得ffmpeg的cmd命令
    public static String[] getCmd(String oldPath,String newPath){
        String cmd="-i "+oldPath+" -f"+" s16le"+" -ar"+" 16000 "+newPath;
        return cmd.split(" ");
    }
}
