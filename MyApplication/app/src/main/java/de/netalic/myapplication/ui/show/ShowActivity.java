package de.netalic.myapplication.ui.show;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.netalic.myapplication.R;

public class ShowActivity extends AppCompatActivity {

    private String mOutput;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            mOutput = extras.getString("output");
        }
        ShowFragment showFragment = (ShowFragment) getSupportFragmentManager().findFragmentById(R.id.layout_show_fragment);
        if (showFragment == null){
            showFragment = ShowFragment.newInstance(mOutput);
        }

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_container, showFragment);
        fragmentTransaction.commit();
    }
}
