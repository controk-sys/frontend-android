package com.example.jourdanrodrigues.controk;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class TextValidator implements TextWatcher {
    private final EditText mEditText;

    protected TextValidator(EditText editText) {
        this.mEditText = editText;
    }

    public abstract void validate(EditText editText);

    @Override
    public void afterTextChanged(Editable s) {
        validate(mEditText);
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
}
