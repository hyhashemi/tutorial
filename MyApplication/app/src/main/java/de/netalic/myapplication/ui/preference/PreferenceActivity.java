package de.netalic.myapplication.ui.preference;

import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;

import de.netalic.myapplication.R;
import de.netalic.myapplication.ui.Base.BaseActivity;

public class PreferenceActivity extends BaseActivity{

    private String mCheckEnable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_activity_layout);

        getFragmentManager().beginTransaction().replace(R.id.preference_fragment_container, new MyPreferenceFragment()).commit();
        setUpToolbar(R.string.preference_title);

    }

    public void turnGPSOn(){
        mCheckEnable = Settings.Secure.getString (getApplicationContext().getContentResolver(),
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        String newSet = String.format ("%s,%s", mCheckEnable, LocationManager.GPS_PROVIDER);
        try {
            Settings.Secure.putString (getApplicationContext().getContentResolver(),
                    Settings.Secure.LOCATION_PROVIDERS_ALLOWED,
                    newSet);
        } catch(Exception e) {}
    }

    public void turnGPSOff(){
        String str = Settings.Secure.getString (getApplicationContext().getContentResolver(),
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (null == str) {
            str = "";
        } else {
            String[] list = str.split (",");
            str = "";
            int j = 0;
            for (int i = 0; i < list.length; i++) {
                if (!list[i].equals (LocationManager.GPS_PROVIDER)) {
                    if (j > 0) {
                        str += ",";
                    }
                    str += list[i];
                    j++;
                }
            }
            mCheckEnable = str;
        }

        try {
            Settings.Secure.putString (getApplicationContext().getContentResolver(),
                    Settings.Secure.LOCATION_PROVIDERS_ALLOWED,
                    mCheckEnable);
        } catch(Exception e) {}
    }
}
