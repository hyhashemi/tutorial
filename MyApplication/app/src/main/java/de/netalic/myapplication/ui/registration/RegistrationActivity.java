package de.netalic.myapplication.ui.registration;

import android.os.Bundle;
import android.support.annotation.Nullable;

import de.netalic.myapplication.R;
import de.netalic.myapplication.ui.Base.BaseActivity;

public class RegistrationActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity_layout);
        RegistrationFragment fragment = (RegistrationFragment) getSupportFragmentManager().findFragmentById(R.id.registration_fragment_layout);
        if (fragment == null){
            fragment = new RegistrationFragment();
            replaceFragment(fragment, R.id.registration_fragment_container);
        }
    }
}
