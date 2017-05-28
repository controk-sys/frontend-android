package com.example.jourdanrodrigues.controk;

public class ClientFragment extends BaseFragment {
    public static final int ARG_MENU_POSITION = 0;

    public int getFragment() {
        return R.layout.fragment_client;
    }

    public int getTitle() {
        return R.string.client_title;
    }

    public ClientFragment() {

    }

    public static ClientFragment newInstance() {
        return new ClientFragment();
    }
}
