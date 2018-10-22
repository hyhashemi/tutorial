package de.netalic.myapplication.ui.enter;

import android.os.Bundle;

import de.netalic.myapplication.R;
import de.netalic.myapplication.ui.Base.BaseActivity;

public class EnterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_activity_layout);
        EnterFragment enterFragment = (EnterFragment) getSupportFragmentManager()
                .findFragmentById(R.id.layout_enter_fragment);
        if (enterFragment == null){
            enterFragment = new EnterFragment();
            replaceFragment(enterFragment, R.id.enter_fragment_container);
        }

        setUpToolbar(R.string.enter_title);
    }
}
