package com.liweisheng.EditNoteActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.liweisheng.Data.UsefulData;
import com.liweisheng.R;
import com.liweisheng.com.liweisheng.Util.JsonParser;

/**
 * Created by 李维升 on 2017/12/20.
 * 这是基于科大讯飞语音识别系统的Activity
 */

public class EditNoteMainActivityBasedXunFei extends AppCompatActivity implements View.OnClickListener {
    private ImageView backBtn;
    private TextView numberOfNote;
    private TextView completeBtn;
    private EditText noteEt;
    private ImageView speakBtn;
    private SpeechRecognizer speechRecognizer;
    private TextView isSpeaking;
    private ImageView upLoadFile;
    private String oldAudioPath;
    private String newAudioPath;
    //转码
    private FFmpeg fFmpeg;

    private StringBuffer stringBuffer=new StringBuffer();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note_activity_main);
        //创建语音配置对象
        SpeechUtility.createUtility(this, SpeechConstant.APPID+"="+ UsefulData.APPIDFORXUNFEI);
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
        //设置文字变动监听，用于监听字数的变化
        noteEt.addTextChangedListener(textWatcher);
        speakBtn = (ImageView) findViewById(R.id.speakBtn);
        speakBtn.setOnClickListener(this);
        isSpeaking=findViewById(R.id.isSpeaking);
        upLoadFile=findViewById(R.id.upLoadFile);
        upLoadFile.setOnClickListener(this);
        speechRecognizer=SpeechRecognizer.createRecognizer(this,initListener);
        initSpeechRecognizer();
        initFFmpeg();
    }
    //初始化ffmpeg
    private void initFFmpeg(){
        fFmpeg=FFmpeg.getInstance(this);
        try {
            fFmpeg.loadBinary(new LoadBinaryResponseHandler(){
                @Override
                public void onFailure() {
                    Toast.makeText(getApplicationContext(),"初始化转码工具失败，您的设备可能不支持",Toast.LENGTH_LONG).show();
                    super.onFailure();
                }
            });
        } catch (FFmpegNotSupportedException e) {
            e.printStackTrace();
        }
    }
    //初始化语音听写参数
    private void initSpeechRecognizer(){
        //设置为日常用语
        speechRecognizer.setParameter(SpeechConstant.DOMAIN,"iat");
        //设置为云端处理
        speechRecognizer.setParameter(SpeechConstant.ENGINE_TYPE,"cloud");
        // 接受的语言是普通话
        speechRecognizer.setParameter(SpeechConstant.ACCENT, "mandarin ");
        // 接收语言中文
        speechRecognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        //持续唤醒
        speechRecognizer.setParameter(SpeechConstant.KEEP_ALIVE,"1");
        //录取音频最长时间为无限
        speechRecognizer.setParameter(SpeechConstant.KEY_SPEECH_TIMEOUT,"-1");
    }
    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            //点击返回按钮
            case R.id.backBtn:
                this.finish();
                break;
            //点击完成按钮
            case R.id.completeBtn:
                break;
            //点击说话按钮
            case R.id.speakBtn:
                speechRecognizer.startListening(recognizerListener);
                isSpeaking.setText("正在听写...");
                break;
            //点击上传录音,调用系统文件选择框，选择文件并使用回调接口
            case R.id.upLoadFile:
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent,"选择录音文件"),UsefulData.FILE_SELECT_CODE);
                break;
        }
    }
    //初始化监听接口
    private InitListener initListener=new InitListener() {
        @Override
        public void onInit(int code) {
            if (code!= ErrorCode.SUCCESS){
                Log.e("doSuccess","初始化失败，错误码是"+code);
                // showTip("初始化失败,错误码：" + code);
                Toast.makeText(getApplicationContext(),"初始化失败,错误码：" + code,
                        Toast.LENGTH_SHORT).show();
            }else {
                Log.e("doSuccess","初始化成功，返回码"+code);
            }
        }
    };
    //语音识别接口
    private RecognizerListener recognizerListener=new RecognizerListener() {
        @Override
        public void onVolumeChanged(int i, byte[] bytes) {

        }

        @Override
        public void onBeginOfSpeech() {

        }
        @Override
        public void onEndOfSpeech() {
                isSpeaking.setText("请重新点击听写");
        }

        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            Log.e("results",recognizerResult.getResultString());
            stringBuffer.append(JsonParser.parserRecognizerResult(recognizerResult.getResultString()));
            if (b=true){
                noteEt.setText(stringBuffer.toString());
            }
        }

        @Override
        public void onError(SpeechError speechError) {
            isSpeaking.setText("ERROR");
            Toast.makeText(getApplicationContext(),speechError.getErrorDescription(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {
        }
    };
    //监听字数变化,监听有没有编辑
    private TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void afterTextChanged(Editable editable) {
            int i=editable.length();
            numberOfNote.setText(String.valueOf(i)+"字");
            //编辑时清除StringBuffer的内容，并且重新添加
            stringBuffer.delete(0,stringBuffer.length());
            stringBuffer.append(noteEt.getText());
        }
    };
    //这是选择文件时回调的接口
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            Log.e("选择文件回调接口发生错误", "onActivityResult() error, resultCode: " + resultCode);
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (requestCode == UsefulData.FILE_SELECT_CODE) {
            Uri uri = data.getData();
            oldAudioPath=uri.getPath();
            Log.i("选择的文件路径为", "------->" + oldAudioPath);
            //ffmpeg -i old.mp3 new.wav
            newAudioPath=oldAudioPath.replace(".mp3","new.wav");
            Log.i("新的文件路径为", "------->" + newAudioPath);
            String[] cmd={"ffmpeg","-i",oldAudioPath,newAudioPath};
            try {
                fFmpeg.execute(cmd,executeBinaryResponseHandler);
            } catch (FFmpegCommandAlreadyRunningException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    //转码回调接口
    private ExecuteBinaryResponseHandler executeBinaryResponseHandler=new ExecuteBinaryResponseHandler(){
        @Override
        public void onStart() {
            super.onStart();
        }
        @Override
        public void onFailure(String message) {
            super.onFailure(message);
        }
        @Override
        public void onFinish() {
            super.onFinish();
        }
        @Override
        public void onProgress(String message) {
            super.onProgress(message);
        }
        @Override
        public void onSuccess(String message) {
            super.onSuccess(message);
        }
    };
}
