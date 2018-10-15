package de.netalic.myapplication.ui.enter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.ParcelableSparseArray;
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

import java.util.ArrayList;
import java.util.List;

import de.netalic.myapplication.R;
import de.netalic.myapplication.data.model.Speciality;
import de.netalic.myapplication.data.model.Wallet;
import de.netalic.myapplication.ui.map.MapActivity;
import de.netalic.myapplication.ui.show.ShowActivity;
import de.netalic.myapplication.ui.wallet.WalletActivity;

import static android.content.Context.MODE_PRIVATE;

public class EnterFragment extends Fragment implements EnterContract.View{

    private View mRootView;
    private EditText mEditText;
    private EnterContract.Presenter mPresenter;
    private String mOutput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.enter_fragment_layout, null);
        Log.e("Registration fragment", "onCreateView: ");
        mPresenter = new EnterPresenter(this);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = mRootView.findViewById(R.id.button_enter_send);
        mEditText = mRootView.findViewById(R.id.editText_enter);
        Toolbar toolbar = mRootView.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(R.string.enter_title);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOutput = mEditText.getText().toString();
                switch(mOutput){
                    case("s"):
                        mPresenter.showRequest();
                        break;

                    case("m"):
                        navigateToMapActivity();
                        break;

                    case("w"):
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
