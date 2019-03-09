package com.agonaika.agonaika;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Test extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    public static final int MULTIPLE_PERMISSIONS = 10;
    String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        requestLocation();
        checkgps();
    }

    public void getlocation() {

//        if (Build.VERSION.SDK_INT >= 23) {
//            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                Log.d("mylog", "Not granted");
//                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//            } else
//                requestLocation();
//        } else
//            requestLocation();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Location mCurrentLocation = locationResult.getLastLocation();
                LatLng myCoordinates = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
                String cityName = getCityName(myCoordinates);
                Toast.makeText(Test.this, cityName, Toast.LENGTH_SHORT).show();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myCoordinates, 13.0f));
//                if (marker == null) {
//                    marker = mMap.addMarker(new MarkerOptions().position(myCoordinates));
//                } else
//                    marker.setPosition(myCoordinates);
            }
        };
    }

    private String getCityName(LatLng myCoordinates) {
        String myCity = "";
        Geocoder geocoder = new Geocoder(Test.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(myCoordinates.latitude, myCoordinates.longitude, 1);
            String address = addresses.get(0).getAddressLine(0);
            myCity = addresses.get(0).getLocality();
            Log.d("mylog", "Complete Address: " + addresses.toString());
            Log.d("mylog", "Address: " + address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myCity;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
    }

    private void requestLocation() {

        if (checkPermissions()) {
            try {

                locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                mLocationCallback = new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        Location mCurrentLocation = locationResult.getLastLocation();
                        LatLng myCoordinates = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
                        String cityName = getCityName(myCoordinates);
                        Toast.makeText(Test.this, cityName, Toast.LENGTH_SHORT).show();
                        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myCoordinates, 13.0f));
//                if (marker == null) {
//                    marker = mMap.addMarker(new MarkerOptions().position(myCoordinates));
//                } else
//                    marker.setPosition(myCoordinates);
                    }
                };

                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
                criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
                String provider = locationManager.getBestProvider(criteria, true);

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//
//
//
//            return;
//        }
                Location location = locationManager.getLastKnownLocation(provider);
                Log.d("mylog", "In Requesting Location");
                if (location != null && (System.currentTimeMillis() - location.getTime()) <= 1000 * 2) {
                    LatLng myCoordinates = new LatLng(location.getLatitude(), location.getLongitude());
                    String cityName = getCityName(myCoordinates);
                    Toast.makeText(this, cityName, Toast.LENGTH_SHORT).show();
                } else {
                    LocationRequest locationRequest = new LocationRequest();
                    locationRequest.setNumUpdates(1);
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    Log.d("mylog", "Last location too old getting new location!");
                    mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                    mFusedLocationClient.requestLocationUpdates(locationRequest, mLocationCallback, Looper.myLooper());
                }


            }catch(SecurityException e){

                e.printStackTrace();

            }
        }

    }




    public boolean checkPermissions() {
         if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true;

        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            if (ContextCompat.checkSelfPermission(this, p) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if(requestCode == MULTIPLE_PERMISSIONS){
            for(String p : permissions){
                if(TextUtils.equals(p, Manifest.permission.ACCESS_FINE_LOCATION) &&
                        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                   requestLocation();
                }
            }
        }
    }



    public void checkgps(){

        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            // Call your Alert message
            Log.i("gps","not on");
        }else{

            Log.i("gps","on");
        }
    }


}
