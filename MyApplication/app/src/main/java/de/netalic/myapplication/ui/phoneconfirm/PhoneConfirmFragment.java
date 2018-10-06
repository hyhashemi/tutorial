package de.netalic.myapplication.ui.phoneconfirm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import de.netalic.myapplication.R;

public class PhoneConfirmFragment extends Fragment implements PhoneConfirmContract.View {

    public View mRootView;
    public EditText mActivationEditText;
    public String mActivationCode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.phone_confirm_fragment_layout, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivationEditText = mRootView.findViewById(R.id.editText_activation_code);
        mActivationCode = getArguments().getString("activationCode");
        mActivationEditText.setText(mActivationCode);
    }

    public static PhoneConfirmFragment newInstance(Bundle extras) {

        Bundle args = extras;

        PhoneConfirmFragment fragment = new PhoneConfirmFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
