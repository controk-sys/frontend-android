package com.example.jourdanrodrigues.controk.Client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jourdanrodrigues.controk.BaseFragment;
import com.example.jourdanrodrigues.controk.Contact.ContactCreationFragment;
import com.example.jourdanrodrigues.controk.R;

public class ClientCreationFragment extends BaseFragment {

    public ClientCreationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        assert view != null;

        view.findViewById(R.id.fab_create_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ClientCreationActivity) getActivity()).updateFragment(new ContactCreationFragment(), "ClientCreationFragment");
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
