package com.agonaika.agonaika;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register2 extends AppCompatActivity {
    public static String firstname;
    public static String lastname;
    public static Integer employeeid;
    public static String company;
    private boolean isedittextok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        final EditText fname = findViewById(R.id.first_name);
        final EditText lname = findViewById(R.id.last_name);
        final EditText empid = findViewById(R.id.employee_id);
        final EditText comp = findViewById(R.id.company_name);

        Button next = findViewById(R.id.reg_next_two);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Register2.firstname = fname.getText().toString();
                Register2.lastname = lname.getText().toString();

                try {
                    Register2.employeeid = Integer.parseInt(empid.getText().toString());
                }
                catch (NumberFormatException e)
                {
                    Register2.employeeid = 0;
                }
                Register2.company = comp.getText().toString();

                checkedittext(Register2.firstname,Register2.lastname,String.valueOf(Register2.employeeid),Register2.company);
                if (isedittextok) {
                    startActivity(new Intent(Register2.this, Register3.class));

                }

            }

        });
    }

    public void checkedittext(String firstnam, String lastnam, String employmntid, String compan){

        if(firstnam.isEmpty()|| lastnam.isEmpty()|| employmntid.isEmpty()|| compan.isEmpty()) {
            Toast.makeText(Register2.this, "All fields are required", Toast.LENGTH_LONG).show();
            isedittextok = false;
            return;
        }
       isedittextok = true;



    }

}
