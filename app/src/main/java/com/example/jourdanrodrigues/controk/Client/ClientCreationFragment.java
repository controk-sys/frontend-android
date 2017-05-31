package com.example.jourdanrodrigues.controk.Client;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.jourdanrodrigues.controk.BaseFragmentCreation;
import com.example.jourdanrodrigues.controk.Contact.ContactCreationFragment;
import com.example.jourdanrodrigues.controk.R;

public class ClientCreationFragment extends BaseFragmentCreation {
    private EditText mName;
    private EditText mEmail;
    private EditText mCpf;
    private EditText mObs;

    public ClientCreationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        assert view != null;

        final ClientCreationActivity activity = (ClientCreationActivity) getActivity();

        view.findViewById(R.id.fab_create_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!errorInFields()) {
                    activity.mClient = new Client(
                        mName.getText().toString(),
                        mEmail.getText().toString(),
                        mCpf.getText().toString(),
                        mObs.getText().toString()
                    );
                    activity.updateFragment(new ContactCreationFragment(), "ClientCreationFragment");
                }
            }
        });

        return view;
    }

    protected Boolean errorInFields() {
        return mName.getError() != null || mEmail.getError() != null || mCpf.getError() != null || mObs.getError() != null;
    }

    protected void initializeFields(View view) {
        mName = ((TextInputLayout) view.findViewById(R.id.client_name_field)).getEditText();
        mEmail = ((TextInputLayout) view.findViewById(R.id.client_email_field)).getEditText();
        mCpf = ((TextInputLayout) view.findViewById(R.id.client_cpf_field)).getEditText();
        mObs = ((TextInputLayout) view.findViewById(R.id.client_obs_field)).getEditText();

        setEmptyFieldValidations(mName);
        setEmptyFieldValidations(mEmail);
        setEmptyFieldValidations(mCpf);
        setEmptyFieldValidations(mObs);
    }

    @Override
    public int getFragment() {
        return R.layout.fragment_client_creation;
    }

    @Override
    public int getTitle() {
        return R.string.client_creation_title;
    }
}
