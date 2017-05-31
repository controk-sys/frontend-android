package com.example.jourdanrodrigues.controk.Contact;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jourdanrodrigues.controk.Address.AddressCreationFragment;
import com.example.jourdanrodrigues.controk.BaseFragment;
import com.example.jourdanrodrigues.controk.BasePersonCreationActivity;
import com.example.jourdanrodrigues.controk.R;

public class ContactCreationFragment extends BaseFragment {
    private TextInputLayout mCellPhone;
    private TextInputLayout mPhone;

    public ContactCreationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        assert view != null;

        mCellPhone = (TextInputLayout) view.findViewById(R.id.cell_phone_field);
        mPhone = (TextInputLayout) view.findViewById(R.id.phone_field);

        final BasePersonCreationActivity activity = (BasePersonCreationActivity) getActivity();

        view.findViewById(R.id.fab_create_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.mContact = new Contact(
                    mCellPhone.getEditText().getText().toString(),
                    mPhone.getEditText().getText().toString()
                );
                activity.updateFragment(new AddressCreationFragment(), "ContactCreationFragment");
            }
        });

        return view;
    }

    @Override
    public int getFragment() {
        return R.layout.fragment_contact_creation;
    }

    @Override
    public int getTitle() {
        return R.string.contact_creation_title;
    }
}
