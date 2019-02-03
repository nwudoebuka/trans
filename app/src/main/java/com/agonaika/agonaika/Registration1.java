package com.agonaika.agonaika;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration1 extends AppCompatActivity {
    public static String email;
    public static String password;
    public boolean isedittextok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration1);
        final EditText emailinput = findViewById(R.id.email);
        final EditText passwordinput = findViewById(R.id.password);
        final EditText confirmpasswordinput = findViewById(R.id.confirm_password);
        //Button next = findViewById(R.id.reg_next_one);


//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                    checkedittext(emailinput.getText().toString(),passwordinput.getText().toString()
//                            ,confirmpasswordinput.getText().toString());
//
//                if (isedittextok) {
//                    Registration1.email = emailinput.getText().toString();
//                    Registration1.password = passwordinput.getText().toString();
//                    startActivity(new Intent(Registration1.this, Register2.class));
//                }
//
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

        isedittextok = true;



    }
}
