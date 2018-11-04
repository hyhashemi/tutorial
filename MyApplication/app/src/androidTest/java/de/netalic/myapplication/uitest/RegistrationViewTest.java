package de.netalic.myapplication.uitest;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.netalic.myapplication.R;
import de.netalic.myapplication.ui.registration.RegistrationActivity;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RegistrationViewTest {

    @Rule
    public IntentsTestRule<RegistrationActivity> registrationActivityIntentsTestRule = new IntentsTestRule<>(RegistrationActivity.class);

    @Test
    public void checkPhoneNumberEditText(){
        Espresso.onView(withId(R.id.registration_phone_number)).perform(typeText("+989141194300"));
        Espresso.onView(withId( R.id.button_registration_send)).perform(click());
    }

}
