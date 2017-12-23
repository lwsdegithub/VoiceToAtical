package com.liweisheng.EditNoteActivity;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
 */

public class EditNoteMainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backBtn;
    private TextView numberOfNote;
    private TextView completeBtn;
    private EditText noteEt;
    private ImageView speakBtn;
    private SpeechRecognizer speechRecognizer;
    private TextView isSpeaking;

    //测试用
    private StringBuffer stringBuffer=new StringBuffer();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note_activity_main);
        //创建语音配置对象
        SpeechUtility.createUtility(this, SpeechConstant.APPID+"="+ UsefulData.APPID);
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
        speechRecognizer=SpeechRecognizer.createRecognizer(this,initListener);
        initSpeechRecognizer();
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
            case R.id.backBtn:
                //返回
                this.finish();
                break;
            case R.id.completeBtn:
                break;
            case R.id.speakBtn:
                speechRecognizer.startListening(recognizerListener);
                isSpeaking.setText("正在听写...");
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
            Toast.makeText(getApplicationContext(),speechError.getErrorDescription(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };
    //文字编辑接口
    private View.OnFocusChangeListener onFocusChangeListener=new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {

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
            stringBuffer.delete(0,stringBuffer.length());
            stringBuffer.append(noteEt.getText());
        }
    };
}
