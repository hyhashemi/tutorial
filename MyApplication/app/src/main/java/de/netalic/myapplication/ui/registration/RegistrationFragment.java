package de.netalic.myapplication.ui.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.netalic.myapplication.R;
import de.netalic.myapplication.ui.show.ShowActivity;

public class RegistrationFragment extends Fragment implements RegistrationContract.View{

    private View mRootView;
    private EditText mEditText;
    private RegistrationContract.Presenter mPresenter;
    private String mOutput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_registration, null);
        Log.e("Registration fragment", "onCreateView: ");
        mPresenter = new RegistrationPresenter(this);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = mRootView.findViewById(R.id.button_registration_send);
        mEditText = mRootView.findViewById(R.id.editText_registration);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOutput = mEditText.getText().toString();
                if (mOutput.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(mRootView, R.string.snackbar_error, Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    mPresenter.request();
                }

            }
        });
    }

    @Override
    public void navigateToShowActivity() {
        Intent show = new Intent(getContext(), ShowActivity.class);
        show.putExtra("output", mOutput);
        startActivity(show);
    }

    @Override
    public void notFound() {
        Toast.makeText(getContext(),R.string.response_error, Toast.LENGTH_LONG);
    }
}
