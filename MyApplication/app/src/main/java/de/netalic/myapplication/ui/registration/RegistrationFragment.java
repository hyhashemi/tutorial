package de.netalic.myapplication.ui.registration;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import de.netalic.myapplication.R;
import de.netalic.myapplication.ui.Base.BaseFragment;
import de.netalic.myapplication.ui.enter.EnterActivity;
import de.netalic.myapplication.ui.phoneconfirm.PhoneConfirmActivity;
import de.netalic.myapplication.utils.SharedPreference;


public class RegistrationFragment extends BaseFragment implements RegistrationContract.View {

    public View mRootView;
    public RegistrationPresenter mRegistrationPresenter;
    public String mPhoneNumber;
    public EditText mEditText;
    public Button mButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.registration_fragment_layout, null);
        mRegistrationPresenter = new RegistrationPresenter(this);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initUi();
        initListener();
        setHasOptionsMenu(true);
    }

    @Override
    public void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPhoneNumber = mEditText.getText().toString();
                SharedPreference sharedPref = new SharedPreference(getActivity().getApplicationContext());
                String phone = sharedPref.getPhoneNumber(mPhoneNumber);
                if (phone.equals(mPhoneNumber)) {
                    navigateToEnter();
                } else {
                    byte[] udIdBytes = Settings.System.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID).getBytes();
                    StringBuilder sb = new StringBuilder();
                    for (byte b : udIdBytes) {
                        sb.append(String.format("%02X", b));
                    }
                    String udId = sb.toString();
                    mRegistrationPresenter.claimRequest(mPhoneNumber, udId);
                }
            }
        });

    }

    @Override
    public void initUi() {
        mEditText = mRootView.findViewById(R.id.registration_phone_number);
        mPhoneNumber = mEditText.getText().toString();
        mButton = mRootView.findViewById(R.id.button_registration_send);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu_enter, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                navigateToSettings();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void navigateToSettings() {
        Intent settings = new Intent(getContext(), de.netalic.myapplication.ui.preference.PreferenceActivity.class);
        startActivity(settings);
    }

    @Override
    public void navigateToPhoneConfirm(){
        Intent confirm = new Intent(getContext(), PhoneConfirmActivity.class);
        confirm.putExtra("phoneNumber", mPhoneNumber);
        startActivity(confirm);
    }

    public void navigateToEnter(){
        Intent enter = new Intent(getContext(), EnterActivity.class);
        startActivity(enter);
    }

    @Override
    public void snackbarError(){
        Snackbar snackbar = Snackbar.make(mRootView, R.string.register_snackbarerror, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}

