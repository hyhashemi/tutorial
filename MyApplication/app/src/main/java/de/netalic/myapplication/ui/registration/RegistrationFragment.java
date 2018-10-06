package de.netalic.myapplication.ui.registration;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import de.netalic.myapplication.R;

public class RegistrationFragment extends Fragment implements RegistrationContract.View {

    public View mRootView;
    public RegistrationPresenter mRegistrationPresenter;
    public String mPhoneNumeber;
    public EditText mEditText;
    public String mActivationCode;
    public String mToken;
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
        mPhoneNumeber = mEditText.getText().toString();
        mButton = mRootView.findViewById(R.id.button_registration_send);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRegistrationPresenter.claimRequest(mPhoneNumeber);
            }
        });

    }

    public void getActivationCode(String activationCode){
        this.mActivationCode = activationCode;
    }

    public void getToken(String token){
        this.mToken = token;
    }

    public String smsRead(){

        return null;
    }
}
