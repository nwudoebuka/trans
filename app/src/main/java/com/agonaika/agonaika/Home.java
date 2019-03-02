package com.agonaika.agonaika;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.agonaika.data.SQLiteDBHelper;
import com.agonaika.data.localdb.LocalDbHelper;
import com.agonaika.data.localdb.dbobject.EmployeeDbo;

import api.ApiInterface;
import api.RetrofitService;
import model.Loginresponse;
import model.Loginresquest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {
Button login_email;
TextView register;
    private SQLiteDBHelper sqLiteDBHelper = null;

    private String DB_NAME = "BookInfo.db";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        login_email = findViewById(R.id.email_login);
        register = findViewById(R.id.register_label);
        setTitle("Login");

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
            contentValues.put("name", empname);
            contentValues.put("member", empmember);
            contentValues.put("phone", empphone);

            sqLiteDatabase.insert(SQLiteDBHelper.EMPLOYEE_TABLE_NAME, null, contentValues);


            Toast.makeText(getApplicationContext(), "Insert data into book table successfully.", Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(getApplicationContext(), "Please create database first.", Toast.LENGTH_LONG).show();
        }

    }
}
