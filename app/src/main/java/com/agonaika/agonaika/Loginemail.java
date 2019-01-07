package com.agonaika.agonaika;

import android.graphics.Paint;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Loginemail extends AppCompatActivity {
TextView firsttime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginemail);
        setTitle("Login");
        firsttime = findViewById(R.id.textViewfirsttime);

       // firsttime.setPaintFlags(firsttime.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }




}
