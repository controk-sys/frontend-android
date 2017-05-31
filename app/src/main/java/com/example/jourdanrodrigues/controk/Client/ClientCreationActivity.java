package com.example.jourdanrodrigues.controk.Client;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.example.jourdanrodrigues.controk.BasePersonCreationActivity;
import com.example.jourdanrodrigues.controk.R;

public class ClientCreationActivity extends BasePersonCreationActivity {
    public Client mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_creation);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.client_creation_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        updateFragment(new ClientCreationFragment(), null);
    }
}
