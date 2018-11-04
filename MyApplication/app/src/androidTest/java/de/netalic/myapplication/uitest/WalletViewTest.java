package de.netalic.myapplication.uitest;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import de.netalic.myapplication.ui.wallet.WalletActivity;

public class WalletViewTest {

    @Rule
    public IntentsTestRule<WalletActivity> walletActivityIntentsTestRule = new IntentsTestRule<>(WalletActivity.class);

    @Before
    public void setUp(){

    }

    @Test
    public void walletAdded(){
        launchWalletActivity();
    }

    private void launchWalletActivity() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), WalletActivity.class);
        walletActivityIntentsTestRule.launchActivity(intent);
    }


}
