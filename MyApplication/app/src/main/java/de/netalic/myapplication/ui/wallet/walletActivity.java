package de.netalic.myapplication.ui.wallet;

import android.os.Bundle;
import android.support.annotation.Nullable;

import de.netalic.myapplication.R;
import de.netalic.myapplication.ui.Base.BaseActivity;

public class walletActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_activity_layout);

        WalletFragment walletFragment = (WalletFragment) getSupportFragmentManager().findFragmentById(R.id.wallet_fragment_layout);
        if (walletFragment == null){
            walletFragment = new WalletFragment();
            replaceFragment(walletFragment, R.id.wallet_fragment_container);
        }
    }
}
