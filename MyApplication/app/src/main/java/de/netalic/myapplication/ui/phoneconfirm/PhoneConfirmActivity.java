package de.netalic.myapplication.ui.phoneconfirm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import de.netalic.myapplication.R;

public class PhoneConfirmActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_confirm_activity_layout);
        Bundle extras = getIntent().getExtras();
        PhoneConfirmFragment fragment = (PhoneConfirmFragment) getSupportFragmentManager().findFragmentById(R.id.phone_confirm_fragment_container);
        if (fragment == null){
            fragment = PhoneConfirmFragment.newInstance(extras);
            replaceFragment(fragment);
        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.phone_confirm_fragment_container, fragment);
        fragmentTransaction.commit();
    }
}

