package de.netalic.myapplication.ui.registration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import de.netalic.myapplication.R;

public class RegistrationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity_layout);
        RegistrationFragment fragment = (RegistrationFragment) getSupportFragmentManager().findFragmentById(R.id.registration_fragment_layout);
        if (fragment == null){
            fragment = new RegistrationFragment();
            replaceFragment(fragment);
        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.registration_fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
