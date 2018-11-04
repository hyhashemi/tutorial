package de.netalic.myapplication.ui.preference;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v14.preference.PreferenceFragment;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import de.netalic.myapplication.R;

public class MyPreferenceFragment extends PreferenceFragment implements GoogleApiClient.OnConnectionFailedListener{

    private static boolean mCheckboxEnabled;
    private CheckBoxPreference mCheckBoxPreference;
    private android.support.v14.preference.SwitchPreference mSwitchPreference;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference_fragment_layout, rootKey);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi();
        initListener();
        getActivity().getApplicationContext();
    }


    private void initUi(){
        mCheckBoxPreference = (CheckBoxPreference) getPreferenceManager().findPreference("checkbox_preference");
        mSwitchPreference = (android.support.v14.preference.SwitchPreference) getPreferenceManager().findPreference("switch_preference");
    }

    private void initListener(){
        mCheckBoxPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Boolean enable = (Boolean) newValue;
                if (enable == true) {
                    mCheckboxEnabled = true;
                }else{
                    mCheckboxEnabled = false;
                }
                return true;
            }
        });

        mSwitchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue instanceof Boolean) {
                    Boolean enable = (Boolean) newValue;
                    if (enable == true) {
                        ((PreferenceActivity) getActivity()).turnGPSOn();
                    } else {
                        ((PreferenceActivity) getActivity()).turnGPSOff();
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void addPreferencesFromResource(int preferencesResId) {
        super.addPreferencesFromResource(preferencesResId);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public static boolean permissionChecked() {
        return mCheckboxEnabled;
    }
}
