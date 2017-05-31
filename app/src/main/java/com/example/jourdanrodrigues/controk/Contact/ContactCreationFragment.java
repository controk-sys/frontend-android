package com.example.jourdanrodrigues.controk.Contact;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jourdanrodrigues.controk.Address.AddressCreationFragment;
import com.example.jourdanrodrigues.controk.BaseFragmentCreation;
import com.example.jourdanrodrigues.controk.BasePersonCreationActivity;
import com.example.jourdanrodrigues.controk.R;

public class ContactCreationFragment extends BaseFragmentCreation {
    private EditText mCellPhone;
    private EditText mPhone;

    public ContactCreationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        assert view != null;

        setActionSendListener();

        view.findViewById(R.id.fab_create_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!errorInFields()) {
                    continueCreation();
                }
            }
        });

        return view;
    }


    private void continueCreation() {
        BasePersonCreationActivity activity = (BasePersonCreationActivity) getActivity();
        activity.mContact = new Contact(
            mCellPhone.getText().toString(),
            mPhone.getText().toString()
        );
        activity.updateFragment(new AddressCreationFragment(), "ContactCreationFragment");
    }


    @Override
    public int getFragment() {
        return R.layout.fragment_contact_creation;
    }

    @Override
    public int getTitle() {
        return R.string.contact_creation_title;
    }

    @Override
    protected Boolean errorInFields() {
        return mCellPhone.getError() != null || mPhone.getError() != null;
    }

    @Override
    protected void initializeFields(View view) {
        mCellPhone = ((TextInputLayout) view.findViewById(R.id.cell_phone_field)).getEditText();
        mPhone = ((TextInputLayout) view.findViewById(R.id.phone_field)).getEditText();

        setEmptyFieldValidations(mCellPhone);
        setEmptyFieldValidations(mPhone);
    }

    private void setActionSendListener() {
        mPhone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    continueCreation();
                    return true;
                }
                return false;
            }
        });
    }
}
