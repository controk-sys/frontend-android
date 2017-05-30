package com.example.jourdanrodrigues.controk;

import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener {

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
    }

    @Override
    public void onFragmentInteraction(String string) {

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
