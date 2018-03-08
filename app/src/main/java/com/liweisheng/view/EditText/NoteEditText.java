package com.liweisheng.view.EditText;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by 李维升 on 2018/3/8.
 */

public class NoteEditText extends AppCompatEditText implements TextWatcher {

    public NoteEditText(Context context) {
        super(context);
        this.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
