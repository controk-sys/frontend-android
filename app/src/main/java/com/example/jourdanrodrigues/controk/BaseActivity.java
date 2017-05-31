package com.example.jourdanrodrigues.controk;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

    public void updateFragment(Fragment fragment, @Nullable String backToFragment) {
        getSupportFragmentManager().beginTransaction()
            .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
            .replace(R.id.content_frame, fragment)
            .addToBackStack(backToFragment)
            .commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStack();
        } else if (fragmentManager.getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
