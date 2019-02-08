package com.agonaika.agonaika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.agonaika.agonaika.Adapters.CustomDialogClass2;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Facercognition extends AppCompatActivity {
CircleImageView usersimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facercognition);
        Button managetime = findViewById(R.id.managetimebtn);
usersimage = findViewById(R.id.userimg);
        Picasso.with(this)
                .load("https://t4.ftcdn.net/jpg/02/15/84/43/240_F_215844325_ttX9YiIIyeaR7Ne6EaLLjMAmy4GvPC69.jpg")
                .placeholder(R.drawable.dummypic)
                .error(R.drawable.dummypic)
                .into(usersimage);

        managetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                startActivity(new Intent(Facercognition.this, Clockin.class));

                CustomDialogClass2 alert = new CustomDialogClass2();
                alert.showDialog(Facercognition.this, "Clocked in Thu 10/01/19 10:05 Am");


            }

        });


    }



}
