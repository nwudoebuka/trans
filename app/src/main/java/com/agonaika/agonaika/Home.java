package com.agonaika.agonaika;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.agonaika.agonaika.Adapters.CustomDialogclassgps;
import com.agonaika.agonaika.Adapters.CustomDialogclasssuccess;
import com.agonaika.data.SQLiteDBHelper;
import com.agonaika.data.localdb.LocalDbHelper;
import com.agonaika.data.localdb.dbobject.EmployeeDbo;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import api.ApiInterface;
import api.RetrofitService;
import model.Loginresponse;
import model.Loginresquest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity implements OnMapReadyCallback {
Button login_email;
TextView register;
    private SQLiteDBHelper sqLiteDBHelper = null;

    private String DB_NAME = "Agonaika.db";

    private int DB_VERSION = 1;

    private String TABLE_NAME = "book";

    public String empname;
    public String empmember;
    public String empphone;
    public String empimage;
    public String emplocation;
    public String emppost;
    public String empmemberconfig;
    public String empuserconfig;
    public String empdevice;
    public String empdepartment;
    public String empshift;
    public String emptime;
    public String empassignment;
    private GoogleMap mMap;
    LocationManager locationManager;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    public static final int MULTIPLE_PERMISSIONS = 10;
    String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        login_email = findViewById(R.id.email_login);
        register = findViewById(R.id.register_label);
        setTitle("Login");
        requestLocation();
        checkgps();
        // Initialize an instance of SQLiteDBHelper.



        login_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Initialize an instance of SQLiteDBHelper.
                sqLiteDBHelper = new SQLiteDBHelper(getApplicationContext(), DB_NAME, null, DB_VERSION);

                if(!hasDBVersionError()) {

                    // This line of code will create the database if not exist. If exist then it will do nothing.
                    // When database is created, the sqLiteDBHelper's onCreate() method will also be invoked,
                    // so book table will be created.
                    sqLiteDBHelper.getWritableDatabase();

                    Toast.makeText(getApplicationContext(), "SQLite database " + DB_NAME + " create successfully.", Toast.LENGTH_LONG).show();

                    empname = "test";
                    empphone = "test";
                    empmember = "test";

                    saveToSQLite();
                }



                //startActivity(new Intent(Home.this, Loginemail.class));
//
//                Toast.makeText(Home.this,
//                        "clicked", Toast.LENGTH_LONG).show();
//
//                //startActivity(new Intent(Loginemail.this, Facercognition.class));
//
//                // Add a new student record
//
//                ContentValues values = new ContentValues();
//
//                values.put(Employeesqlitedb.NAME,
//                        "John Doe");
//
//                values.put(Employeesqlitedb.MEMBER,
//                        "Clocked in");
//
////                 Retrieve student records
////                String URL = "content://com.agonaika.data.localdb/employee";
//
//                String URL = "content://com.agonaika.data.test.BaseContentProvider";
//
//                Uri employeeURI = Uri.parse(URL);
//                // Uri uri = getContentResolver().insert(LocalDbHelper.CONTENT_URI, values);
//
//                BaseContentProvider provider = new BaseContentProvider(this);
//                provider.onCreate();
//                provider.insert(employeeURI, values);

                //Employeesqlitedb n = new Employeesqlitedb();
                //n.insert(employeeURI, values);

                //Toast.makeText(getBaseContext(), n.insert(employeeURI, values).toString(), Toast.LENGTH_LONG).show();


//                ContentValues values = new ContentValues();
//
//                values.put(Employeesqlitedb.NAME,
//                        "John Doe");
//
//                values.put(Employeesqlitedb.STATUS,
//                        "Clocked in");
//
//                Uri uri = getContentResolver().insert(
//                        Employeesqlitedb.CONTENT_URI, values);

                //Toast.makeText(getBaseContext(),
                        //uri.toString(), Toast.LENGTH_LONG).show();


            }

        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Home.this, Registration1.class));


            }

        });

    }



    private boolean hasDBVersionError()
    {
        boolean ret = false;
        try
        {
            SQLiteDatabase sqliteDatabase = sqLiteDBHelper.getReadableDatabase();
        }catch(SQLiteException ex)
        {
            ret = true;

            String errorMessage = ex.getMessage();

            Log.d(SQLiteDBHelper.LOG_TAG_SQLITE_DB, errorMessage, ex);

            if(errorMessage.startsWith("Can't downgrade database from version"))
            {
                Toast.makeText(getApplicationContext(), errorMessage + " , please remove sqlite database by uninstall this app first.", Toast.LENGTH_LONG).show();
            }else
            {
                Toast.makeText(getApplicationContext(), "Create db error, error message is " + errorMessage, Toast.LENGTH_LONG).show();
            }
        }finally {
            return ret;
        }
    }


    public void loginwithEmail(String email, String password){





//        dialog = new ProgressDialog(Register3.this);
//        dialog.setMessage("Please wait...");
//        dialog.show();
        ApiInterface api = RetrofitService.initializer();
        Loginresquest login =  new Loginresquest(email,password);
        Call<Loginresponse> call = api.loginuser(login);
        call.enqueue(new Callback<Loginresponse>() {
            @Override
            public void onResponse(Call<Loginresponse> call, Response<Loginresponse> response) {
                //Toast.makeText(Register3.this,response.code()+"",Toast.LENGTH_LONG).show();
                if(response.code() == 404) {

                }else if(response.code() == 200){



                    if (response.isSuccessful()) {
                        Loginresponse loginresp = response.body();
                        empname = loginresp.getEmployeename();
                        empphone = loginresp.getPhone();
                        empmember = loginresp.getMember();

                        saveToSQLite();
//                            Toast.makeText(context.getBaseContext(),
//                                    n.insert(employeeURI, values).toString(), Toast.LENGTH_LONG).show();




                    }else{



                    }

                }





            }

            @Override
            public void onFailure(Call<Loginresponse> call, Throwable t) {


            }
        });





    }

    public void saveToSQLite(){


        if(sqLiteDBHelper!=null) {
            // Create the database tables again, this time because database version increased so the onUpgrade() method is invoked.
            SQLiteDatabase sqLiteDatabase = sqLiteDBHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            // Then insert first row into book table.
            contentValues.clear();
            contentValues.put("firstName", empname);
            contentValues.put("affiliateName", empmember);
            contentValues.put("phone", empphone);

            sqLiteDatabase.insert(SQLiteDBHelper.USER_SIGN_IN_TABLE_NAME, null, contentValues);


            Toast.makeText(getApplicationContext(), "Insert data into book table successfully.", Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(getApplicationContext(), "Please create database first.", Toast.LENGTH_LONG).show();
        }

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
                Toast.makeText(Home.this, cityName, Toast.LENGTH_SHORT).show();
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
        Geocoder geocoder = new Geocoder(Home.this, Locale.getDefault());
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
                        Toast.makeText(Home.this, cityName, Toast.LENGTH_SHORT).show();
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


            CustomDialogclassgps alert = new CustomDialogclassgps();
            alert.showDialog(Home.this, "You need to enable GPS to use Agonaika, click ok to go to settings and turn On GPS.");

            Log.i("gps","not on");
        }else{

            Log.i("gps","on");
        }
    }



}
