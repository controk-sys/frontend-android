package com.example.jourdanrodrigues.controk.Client;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jourdanrodrigues.controk.BaseFragment;
import com.example.jourdanrodrigues.controk.Contact.ContactCreationFragment;
import com.example.jourdanrodrigues.controk.R;

public class ClientCreationFragment extends BaseFragment {
    private TextInputLayout mName;
    private TextInputLayout mEmail;
    private TextInputLayout mCpf;
    private TextInputLayout mObs;

    public ClientCreationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        assert view != null;

        mName = (TextInputLayout) view.findViewById(R.id.client_name_field);
        mEmail = (TextInputLayout) view.findViewById(R.id.client_email_field);
        mCpf = (TextInputLayout) view.findViewById(R.id.client_cpf_field);
        mObs = (TextInputLayout) view.findViewById(R.id.client_obs_field);

        final ClientCreationActivity activity = (ClientCreationActivity) getActivity();

        view.findViewById(R.id.fab_create_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.mClient = new Client(
                    mName.getEditText().getText().toString(),
                    mEmail.getEditText().getText().toString(),
                    mCpf.getEditText().getText().toString(),
                    mObs.getEditText().getText().toString()
                );
                activity.updateFragment(new ContactCreationFragment(), "ClientCreationFragment");
            }
        });

        return view;
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
