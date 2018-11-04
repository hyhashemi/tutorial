package de.netalic.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import de.netalic.myapplication.utils.SharedPreference;

import static org.junit.Assert.assertEquals;

public class SharedPreferenceTest {

    SharedPreference sharedPreference;
    @Before
    public void before(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        sharedPreference = new SharedPreference(appContext);
    }

    @Test
    public void setTokenTest(){
       sharedPreference.setToken("token");
       assertEquals(sharedPreference.getToken(),"token" );
    }

    @Test
    public void tokenDefaultTest(){
        sharedPreference.delete("token");
        assertEquals(sharedPreference.getToken(), "default");
    }

    @Test
    public void phoneNumberDefaultTest(){
        assertEquals(sharedPreference.getPhoneNumber("123456"),"default" );
    }

    @Test
    public void setPhoneNumberTest(){
        sharedPreference.setPhoneNumber("+989128111411");
        assertEquals(sharedPreference.getPhoneNumber("+989128111411"), "+989128111411");
    }
}
