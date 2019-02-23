package com.agonaika.agonaika;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import model.Publicfunctions;

public class Registration1 extends AppCompatActivity {
    public static String email;
    public static String password;
    public boolean isedittextok;
    Publicfunctions pubfunc = new Publicfunctions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration1);
        final EditText emailinput = findViewById(R.id.email);
        final EditText passwordinput = findViewById(R.id.password);
        final EditText confirmpasswordinput = findViewById(R.id.confirm_password);
        //ImageView backbtn = findViewById(R.id.back_reg_1);
        Button next = findViewById(R.id.reg_next_one);

        setTitle("Register");

        if(getActionBar()!= null){
            getActionBar().setDisplayHomeAsUpEnabled(true);

        }

        pubfunc.don(confirmpasswordinput);
        //backbtn.setClickable(true);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    checkedittext(emailinput.getText().toString(),passwordinput.getText().toString()
                            ,confirmpasswordinput.getText().toString());

                if (isedittextok) {
                    Registration1.email = emailinput.getText().toString();
                    Registration1.password = passwordinput.getText().toString();
                    startActivity(new Intent(Registration1.this, Register2.class));
                }


            }

        });


//        backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Registration1.this, Home.class));
//
//            }
//
//        });


    }

    public void checkedittext(String email, String password, String confirmpassword){

        if(email.isEmpty()|| password.isEmpty()|| confirmpassword.isEmpty()) {
            Toast.makeText(Registration1.this, "All fields are required", Toast.LENGTH_LONG).show();
           isedittextok = false;
           return;
        }
        if (!password.equalsIgnoreCase(confirmpassword)){
            Toast.makeText(Registration1.this,"Passwords do not match",Toast.LENGTH_LONG).show();
          isedittextok = false;
          return;
        }
    if(!pubfunc.isEmailValid(email)){
        Toast.makeText(Registration1.this,"Email address is not valid!",Toast.LENGTH_LONG).show();
        return;
    }
        isedittextok = true;



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_general, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;
            case R.id.settings:
                break;
//            case R.id.logout:
//                break;

        }
        return true;
    }
}
