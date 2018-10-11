package de.netalic.myapplication.ui.phoneconfirm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import de.netalic.myapplication.R;
import de.netalic.myapplication.ui.Base.BaseActivity;

public class PhoneConfirmActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_confirm_activity_layout);
        Bundle extras = getIntent().getExtras();
        PhoneConfirmFragment fragment = (PhoneConfirmFragment) getSupportFragmentManager().findFragmentById(R.id.phone_confirm_fragment_container);
        if (fragment == null){
            fragment = PhoneConfirmFragment.newInstance(extras);
            replaceFragment(fragment, R.id.phone_confirm_fragment_container);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.phoneconfirm_title);
    }
}

