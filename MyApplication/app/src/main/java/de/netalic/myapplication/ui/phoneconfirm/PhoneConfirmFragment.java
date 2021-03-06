package de.netalic.myapplication.ui.phoneconfirm;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import de.netalic.myapplication.R;
import de.netalic.myapplication.receiver.ISmsBroadcastReceiver;
import de.netalic.myapplication.ui.enter.EnterActivity;

public class PhoneConfirmFragment extends Fragment implements PhoneConfirmContract.View {

    public View mRootView;
    public EditText mActivationEditText;
    public PhoneConfirmPresenter mPhoneConfirmPresenter;
    public String mPhoneNumber;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.phone_confirm_fragment_layout, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mPhoneConfirmPresenter = new PhoneConfirmPresenter(this);
        mActivationEditText = mRootView.findViewById(R.id.editText_activation_code);
        mPhoneNumber = getArguments().getString("phoneNumber");
        mActivationEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mActivationEditText.getText().toString().length() == 6) {
                    mPhoneConfirmPresenter.bindRequest(mActivationEditText.getText().toString(), mPhoneNumber);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        checkPermission();

        ISmsBroadcastReceiver ISmsBroadcastReceiver = new ISmsBroadcastReceiver(new ISmsBroadcastReceiver.ISmsInterface() {
            @Override
            public void onDone(String text) {
                mActivationEditText.setText(text);
            }
        });

        getActivity().registerReceiver(ISmsBroadcastReceiver, new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION));

    }

    public static PhoneConfirmFragment newInstance(Bundle extras) {
        Bundle args = extras;
        PhoneConfirmFragment fragment = new PhoneConfirmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_SMS}, 1);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECEIVE_SMS}, 1);
        }
    }

    @Override
    public void navigateToShow() {
        Intent enter = new Intent(getContext(), EnterActivity.class);
        startActivity(enter);
    }

    @Override
    public void snackbarError() {
        Snackbar snackbar = Snackbar.make(mRootView, R.string.phoneconfirm_snackbarerror, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
