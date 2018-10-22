package de.netalic.myapplication.ui.enter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import de.netalic.myapplication.R;
import de.netalic.myapplication.data.model.Speciality;
import de.netalic.myapplication.data.model.Wallet;
import de.netalic.myapplication.ui.Base.BaseFragment;
import de.netalic.myapplication.ui.map.MapActivity;
import de.netalic.myapplication.ui.show.ShowActivity;
import de.netalic.myapplication.ui.wallet.WalletActivity;

public class EnterFragment extends BaseFragment implements EnterContract.View{

    private View mRootView;
    private AutoCompleteTextView mEditText;
    private EnterContract.Presenter mPresenter;
    private String mEditTextOutput;
    private final String[] views = {"speciality", "map", "wallet"};
    private Button mButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.enter_fragment_layout, null);
        mPresenter = new EnterPresenter(this);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi();
        initListener();
    }

    @Override
    public void initUi() {
        mButton = mRootView.findViewById(R.id.button_enter_send);
        mEditText = mRootView.findViewById(R.id.editText_enter);
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.select_dialog_singlechoice, views);
        mEditText.setThreshold(1);
        mEditText.setAdapter(arrayAdapter);
    }

    @Override
    public void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditTextOutput = mEditText.getText().toString();
                switch(mEditTextOutput){
                    case "speciality":
                        mPresenter.showRequest();
                        break;

                    case "map":
                        navigateToMapActivity();
                        break;

                    case "wallet":
                        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
                        String token = sharedPref.getString("token", "default" );
                        Log.e("token", token );
                        mPresenter.walletRequest(token);
                        break;

                    default:
                        Snackbar snackbar = Snackbar.make(mRootView, R.string.enter_snackbarerror, Snackbar.LENGTH_LONG);
                        snackbar.show();
                        break;
                }


            }
        });
    }

    @Override
    public void navigateToShowActivity(List<Speciality> body) {
        Intent show = new Intent(getContext(), ShowActivity.class);
        show.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) body);
        startActivity(show);
    }

    @Override
    public void navigateToMapActivity() {
        Intent map = new Intent(getContext(), MapActivity.class);
        startActivity(map);
    }

    @Override
    public void navigateToWalletActivity(List<Wallet> body) {
        Intent wallet = new Intent(getContext(), WalletActivity.class);
        wallet.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) body);
        startActivity(wallet);
    }

}
