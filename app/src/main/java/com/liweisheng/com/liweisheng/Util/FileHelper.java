package com.liweisheng.com.liweisheng.Util;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 李维升 on 2018/2/10.
 */

public class FileHelper {
    private Context context;

    public FileHelper(Context context) {
        this.context = context;
    }
    public void saveFile(String fileName,String fileContent) throws IOException {
        FileOutputStream fileOutputStream=context.openFileOutput(fileName,Context.MODE_PRIVATE);
        fileOutputStream.write(fileContent.getBytes());
        fileOutputStream.close();
    }
}
