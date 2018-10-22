package de.netalic.myapplication.ui.Base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import de.netalic.myapplication.R;

public abstract class BaseActivity extends AppCompatActivity{

    private Toolbar mToolbar;
    public void replaceFragment(Fragment fragment, int res){

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(res, fragment);
        fragmentTransaction.commit();
    }

    public void setUpToolbar(int wallet_title){
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(wallet_title);
    }


}
