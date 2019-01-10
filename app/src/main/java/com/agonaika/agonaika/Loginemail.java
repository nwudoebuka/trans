package com.agonaika.agonaika;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Loginemail extends AppCompatActivity {
TextView firsttime,forgotpassword;
Button login;
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

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Loginemail.this, Facercognition.class));


            }

        });

    }




}
