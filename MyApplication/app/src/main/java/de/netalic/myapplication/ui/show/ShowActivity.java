package de.netalic.myapplication.ui.show;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import de.netalic.myapplication.R;
import de.netalic.myapplication.data.model.Speciality;

public class ShowActivity extends AppCompatActivity {

    private List<Speciality> mData;
    private final static String DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            mData = extras.getParcelableArrayList(DATA);
        }

        ShowFragment showFragment = (ShowFragment) getSupportFragmentManager().findFragmentById(R.id.layout_show_fragment);
        if (showFragment == null){
            showFragment = ShowFragment.newInstance(mData);
        }

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_container, showFragment);
        fragmentTransaction.commit();
    }

}
