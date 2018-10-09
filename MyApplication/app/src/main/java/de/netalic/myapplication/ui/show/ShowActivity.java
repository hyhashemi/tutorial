package de.netalic.myapplication.ui.show;

import android.os.Bundle;

import java.util.List;

import de.netalic.myapplication.R;
import de.netalic.myapplication.data.model.Speciality;
import de.netalic.myapplication.ui.Base.BaseActivity;

public class ShowActivity extends BaseActivity{

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
            replaceFragment(showFragment, R.id.show_fragment_container);
        }
    }

}
