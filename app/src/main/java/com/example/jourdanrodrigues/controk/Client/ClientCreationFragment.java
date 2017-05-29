package com.example.jourdanrodrigues.controk.Client;

import com.example.jourdanrodrigues.controk.BaseFragment;
import com.example.jourdanrodrigues.controk.R;

public class ClientCreationFragment extends BaseFragment {
    public int getFragment() {
        return R.layout.fragment_client_creation;
    }

    public int getTitle() {
        return R.string.client_title;
    }

    public ClientCreationFragment() {

    }

    public static ClientCreationFragment newInstance() {
        return new ClientCreationFragment();
    }
}
