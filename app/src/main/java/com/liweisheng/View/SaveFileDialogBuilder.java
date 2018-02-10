package com.liweisheng.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.liweisheng.R;
import com.liweisheng.com.liweisheng.Util.FileHelper;

import java.io.IOException;

/**
 * Created by 李维升 on 2018/2/10.
 */

public class SaveFileDialogBuilder extends AlertDialog.Builder implements DialogInterface.OnClickListener {
    private Context context;
    private String fileName;
    private String fileContent;
    private FileHelper fileHelper;
    private EditText editText;
    private boolean isCanceled=false;

    public SaveFileDialogBuilder(@NonNull Context context,String fileContent) throws IOException {
        super(context);
        this.context=context;
        this.fileContent=fileContent;
        this.init();
    }
    private void init() throws IOException {
        this.fileHelper=new FileHelper(this.context);
        this.setNegativeButton("取消",this);
        this.setPositiveButton("确定",this);
        this.setTitle("保存文件");
        View mainView= LayoutInflater.from(context).inflate(R.layout.save_file_dialog_layout,null,false);
        this.setView(mainView);
        this.editText=mainView.findViewById(R.id.file_name_ET);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i==0) try {
            this.fileName=editText.getText().toString();
            fileHelper.saveFile(fileName,fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (i==1){
            isCanceled=true;
        }
    }
    public boolean getIsCanceld(){
        return isCanceled;
    }
}
