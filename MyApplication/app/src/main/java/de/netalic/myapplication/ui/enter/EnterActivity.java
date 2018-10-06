package de.netalic.myapplication.ui.enter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import de.netalic.myapplication.R;

public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_activity_layout);
        EnterFragment registrationFragment = (EnterFragment) getSupportFragmentManager()
                .findFragmentById(R.id.layout_enter_fragment);
        if (registrationFragment == null){
            registrationFragment = new EnterFragment();
        }
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.enter_fragment_container, registrationFragment);
        fragmentTransaction.commit();
    }

}
