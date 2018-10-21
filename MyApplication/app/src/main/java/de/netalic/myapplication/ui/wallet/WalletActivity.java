package de.netalic.myapplication.ui.wallet;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.List;

import de.netalic.myapplication.R;
import de.netalic.myapplication.data.model.Wallet;
import de.netalic.myapplication.ui.Base.BaseActivity;

public class WalletActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_activity_layout);
        WalletFragment walletFragment = (WalletFragment) getSupportFragmentManager().findFragmentById(R.id.wallet_fragment_layout);

        List<Wallet> data = getIntent().getParcelableArrayListExtra("data");
        if (walletFragment == null){
            walletFragment = WalletFragment.newInstance(data);
            replaceFragment(walletFragment, R.id.wallet_fragment_container);
        }
    }
}
