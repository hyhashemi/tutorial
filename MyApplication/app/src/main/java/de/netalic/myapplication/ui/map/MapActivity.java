package de.netalic.myapplication.ui.map;

import android.os.Bundle;
import android.support.annotation.Nullable;

import de.netalic.myapplication.R;
import de.netalic.myapplication.ui.Base.BaseActivity;

public class MapActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity_layout);
        MapFragment mapFragment = (MapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        if (mapFragment == null){
            mapFragment = new MapFragment();
            replaceFragment(mapFragment, R.id.map_fragment_container);
        }
    }
}
