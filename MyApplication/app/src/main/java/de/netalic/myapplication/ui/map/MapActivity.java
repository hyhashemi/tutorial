package de.netalic.myapplication.ui.map;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import de.netalic.myapplication.R;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener{

    public final int LOCATION_REQUEST_CODE = 99;
    public GoogleApiClient googleApiClient;
    public GoogleMap mMap;
    public SupportMapFragment mapFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity_layout);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onStart() {
        super.onStart();
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        googleApiClient.connect();
    }

    public void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                    //Permission Granted
                } else
                    Toast.makeText(this, "Location Permission Denied", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                    android.Manifest.permission.ACCESS_FINE_LOCATION
            }, LOCATION_REQUEST_CODE);
            return;
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        checkLocationandAddToMap();
    }

    private void checkLocationandAddToMap() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION
            }, LOCATION_REQUEST_CODE);
            return;
        }
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true));
        Location location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null){
            MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("You are Here");
            mMap.addMarker(markerOptions);
        }
        locationManager.requestLocationUpdates(bestProvider, 1000, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("You are Here");
        mMap.addMarker(markerOptions);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
