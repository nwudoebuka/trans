package com.agonaika.agonaika;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.agonaika.agonaika.Adapters.CustomDialogClassfail;
import com.agonaika.agonaika.Adapters.CustomDialogclasssuccess;
//import com.agonaika.data.localdb.AgoWorkSqlOpenHelper;
import com.agonaika.data.localdb.LocalDbHelper;
import com.agonaika.data.localdb.dbobject.EmployeeDbo;

import api.ApiInterface;
import api.RetrofitService;
import model.Loginresponse;
import model.Loginresquest;
import model.Registerrequest;
import model.Registerresp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Loginemail extends AppCompatActivity {
TextView firsttime,forgotpassword;
Button login;
public String Employeename;
public String Employeestatus;
public String Phone;
public String Membername;
public String Lastlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginemail);
        setTitle("Login");
        firsttime = findViewById(R.id.textViewfirsttime);
        forgotpassword = findViewById(R.id.textViewforgotpassword);
        login = findViewById(R.id.emaillogintbn);

        firsttime.setPaintFlags(firsttime.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        forgotpassword.setPaintFlags(forgotpassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //AgoWorkSqlOpenHelper agosql = new AgoWorkSqlOpenHelper(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(Loginemail.this,
                        "clicked", Toast.LENGTH_LONG).show();

                //startActivity(new Intent(Loginemail.this, Facercognition.class));

                // Add a new student record
                ContentValues values = new ContentValues();
                values.put(EmployeeDbo.COL_BADGE_NUMBER,"test");

                // Retrieve student records
                String URL = "content://com.agonaika.mobile.provider/employee";

                Uri employeeURI = Uri.parse(URL);
                // Uri uri = getContentResolver().insert(LocalDbHelper.CONTENT_URI, values);

                LocalDbHelper n = new LocalDbHelper();
                n.insert(employeeURI, values);

                Toast.makeText(getBaseContext(),
                        n.insert(employeeURI, values).toString(), Toast.LENGTH_LONG).show();


            }

        });

    }



    public void loginfunc(String email, String password){


//        dialog = new ProgressDialog(Register3.this);
//        dialog.setMessage("Please wait...");
//        dialog.show();
        ApiInterface api = RetrofitService.initializer();
        Loginresquest login =  new Loginresquest("test","test");
        Call<Loginresponse> call = api.loginuser(login);
        call.enqueue(new Callback<Loginresponse>() {
            @Override
            public void onResponse(Call<Loginresponse> call, Response<Loginresponse> response) {
                //Toast.makeText(Register3.this,response.code()+"",Toast.LENGTH_LONG).show();
                if(response.code() == 404) {

                }else if(response.code() == 200){



                    if (response.isSuccessful()) {
                        Loginresponse loginresp = response.body();
                        Employeename = loginresp.getEmployeename();
                        Phone = loginresp.getPhone();
                        Membername = loginresp.getMember();
                        Employeestatus = loginresp.getStatus();
                        Lastlogin = loginresp.getLast_login();


                        // Add a new student record
                        ContentValues values = new ContentValues();
                        values.put(EmployeeDbo.COL_BADGE_NUMBER,"test");

                        // Retrieve student records
                        String URL = "content://com.agonaika.LocalDbHelper";

                        Uri employeeURI = Uri.parse(URL);
                       // Uri uri = getContentResolver().insert(LocalDbHelper.CONTENT_URI, values);

                        LocalDbHelper n = new LocalDbHelper();
                        n.insert(employeeURI, values);

                        Toast.makeText(getBaseContext(),
                                n.insert(employeeURI, values).toString(), Toast.LENGTH_LONG).show();


                    }else{



                    }

                }





            }

            @Override
            public void onFailure(Call<Loginresponse> call, Throwable t) {


            }
        });

    }



}
