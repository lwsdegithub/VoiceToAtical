package com.liweisheng.EditNoteActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.liweisheng.R;

/**
 * Created by 李维升 on 2017/12/20.
 */

public class EditNoteMainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backBtn;
    private TextView numberOfNote;
    private TextView completeBtn;
    private EditText noteEt;
    private ImageView speakBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note_activity_main);
        //调用初始化界面方法
        this.initView();
    }
    private void initView(){
        backBtn = (ImageView) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);
        numberOfNote = (TextView) findViewById(R.id.numberOfNote);
        completeBtn = (TextView) findViewById(R.id.completeBtn);
        completeBtn.setOnClickListener(this);
        noteEt = (EditText) findViewById(R.id.noteEt);
        speakBtn = (ImageView) findViewById(R.id.speakBtn);
        speakBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.backBtn:
                this.finish();
                break;
            case R.id.completeBtn:
                break;
            case R.id.speakBtn:
                break;
        }
    }
}
