package de.netalic.myapplication.uitest;

import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

import de.netalic.myapplication.R;
import de.netalic.myapplication.ui.phoneconfirm.PhoneConfirmActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class PhoneConfirmViewTest {

    @Rule
    public IntentsTestRule<PhoneConfirmActivity> phoneConfirmActivityIntentsTestRule = new IntentsTestRule<>(PhoneConfirmActivity.class);

}
