package de.netalic.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.netalic.myapplication.ui.registration.RegistrationActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent registration = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(registration);
    }
}
