package com.example.jourdanrodrigues.controk.Client;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.example.jourdanrodrigues.controk.Address.Address;
import com.example.jourdanrodrigues.controk.BaseActivity;
import com.example.jourdanrodrigues.controk.Contact.Contact;
import com.example.jourdanrodrigues.controk.R;

public class ClientCreationActivity extends BaseActivity {
    public Address mAddress;
    public Client mClient;
    public Contact mContact;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
