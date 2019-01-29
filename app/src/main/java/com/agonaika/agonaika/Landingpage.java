package com.agonaika.agonaika;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Landingpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        LinearLayout timetracking = findViewById(R.id.time_tracking_lin);

        timetracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



              startActivity(new Intent(Landingpage.this, Entertime2.class));


            }

        });
    }
}
