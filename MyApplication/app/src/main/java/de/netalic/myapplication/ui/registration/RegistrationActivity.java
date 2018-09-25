package de.netalic.myapplication.ui.registration;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import de.netalic.myapplication.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.e("Registration Activity", "onCreate");
        setContentView(R.layout.activity_registraion);
        RegistrationFragment registrationFragment = (RegistrationFragment) getSupportFragmentManager().findFragmentById(R.id.layout_registration_fragment);
        if (registrationFragment == null){
            registrationFragment = new RegistrationFragment();
        }
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.registration_fragment_container, registrationFragment);
        fragmentTransaction.commit();
    }

}
