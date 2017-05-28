package com.example.jourdanrodrigues.controk;

public class ClientCreationFragment extends BaseFragment {
    public static final int ARG_MENU_POSITION = 0;

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
