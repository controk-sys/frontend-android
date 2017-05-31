package com.example.jourdanrodrigues.controk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public abstract class BaseFragmentCreation extends BaseFragment{

    protected abstract Boolean errorInFields();
    protected abstract void initializeFields(View view);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        initializeFields(view);

        return view;
    }

    protected void validateEmptyField(EditText editText) {
        editText.setError(editText.getText().toString().isEmpty() ? "Field cannot be empty!" : null);
    }

    protected void setEmptyFieldValidations(final EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { validateEmptyField(editText); }
        });
        editText.addTextChangedListener(new TextValidator(editText) {
            @Override
            public void validate(EditText innerEditText) { validateEmptyField(innerEditText); }
        });
    }
}
