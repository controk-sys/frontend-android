package com.example.jourdanrodrigues.controk.Contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jourdanrodrigues.controk.Address.AddressCreationFragment;
import com.example.jourdanrodrigues.controk.BaseFragment;
import com.example.jourdanrodrigues.controk.Client.ClientCreationActivity;
import com.example.jourdanrodrigues.controk.R;

public class ContactCreationFragment extends BaseFragment {

    public ContactCreationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        assert view != null;

        view.findViewById(R.id.fab_create_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ClientCreationActivity) getActivity()).updateFragment(new AddressCreationFragment(), "ContactCreationFragment");
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
