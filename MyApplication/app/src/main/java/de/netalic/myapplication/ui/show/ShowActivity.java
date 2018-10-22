package de.netalic.myapplication.ui.show;

import android.os.Bundle;

import java.util.List;

import de.netalic.myapplication.R;
import de.netalic.myapplication.data.model.Speciality;
import de.netalic.myapplication.ui.Base.BaseActivity;

public class ShowActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_activity_layout);
        List<Speciality> data = getIntent().getExtras().getParcelableArrayList("data");
        ShowFragment showFragment = (ShowFragment) getSupportFragmentManager().findFragmentById(R.id.layout_show_fragment);
        if (showFragment == null){
            showFragment = ShowFragment.newInstance(data);
            replaceFragment(showFragment, R.id.show_fragment_container);
        }

        setUpToolbar(R.string.show_title);
    }

}
