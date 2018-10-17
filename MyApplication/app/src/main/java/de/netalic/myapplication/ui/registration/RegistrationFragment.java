package de.netalic.myapplication.ui.registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import de.netalic.myapplication.R;
import de.netalic.myapplication.ui.enter.EnterActivity;
import de.netalic.myapplication.ui.phoneconfirm.PhoneConfirmActivity;

public class RegistrationFragment extends Fragment implements RegistrationContract.View {

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
        mEditText = mRootView.findViewById(R.id.phone_number);
        mPhoneNumber = mEditText.getText().toString();
        mButton = mRootView.findViewById(R.id.button_registration_send);
        Toolbar toolbar = mRootView.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Register");

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPhoneNumber = mEditText.getText().toString();
                Log.e("phone", String.valueOf(mPhoneNumber));
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
                String phone = sharedPref.getString("phone", "default" );
                if (phone.equals(mPhoneNumber)){
                    navigateToEnter();
                }else{
                    mRegistrationPresenter.claimRequest(mPhoneNumber);
                    }
            }
        });
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

