package com.agonaika.agonaika;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Clockin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockin);

        Button clockout = findViewById(R.id.clockoutbtn);
        Button clockin = findViewById(R.id.clockinbtn);

        clockout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Clockin.this, Changedept.class));


            }

        });



        clockin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CustomDialogClass alert = new CustomDialogClass();
                alert.showDialog(Clockin.this, "Clocked in Thu 10/01/19 10:05 Am");
            }

        });
    }
}
