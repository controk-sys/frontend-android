package com.example.jourdanrodrigues.controk;

import android.view.MenuItem;

import com.example.jourdanrodrigues.controk.Address.Address;
import com.example.jourdanrodrigues.controk.Contact.Contact;

public class BasePersonCreationActivity extends BaseActivity {
    public Address mAddress;
    public Contact mContact;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
