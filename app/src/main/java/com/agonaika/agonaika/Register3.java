package com.agonaika.agonaika;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.agonaika.agonaika.Adapters.CustomDialogClassfail;
import com.agonaika.agonaika.Adapters.CustomDialogclasssuccess;

import java.util.ArrayList;
import java.util.List;

import api.ApiInterface;
import api.Constant;
import api.RetrofitService;
import model.Publicfunctions;
import model.Registerrequest;
import model.Registerresp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ProgressDialog dialog;
    public String toastmsg;
    private boolean isedittextok;
    private boolean isswitched;
    Publicfunctions pubfunc = new Publicfunctions();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Register");
        setContentView(R.layout.activity_register3);
        isswitched = false;
        final EditText mobile = findViewById(R.id.mobile_number);
        final Switch notification = findViewById(R.id.switch1);
        //ImageView backbtn = findViewById(R.id.back_reg_3);
        Button registerbtn = findViewById(R.id.reg_submit);
        final RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        final LinearLayout notificationlin = findViewById(R.id.notification_lin);
        pubfunc.don(mobile);
        PhoneNumberUtils.formatNumber(mobile.getText().toString());
        notificationlin.setVisibility(View.GONE);
        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

//        backbtn.setClickable(true);
//        backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Register3.this, Register2.class));
//
//            }
//
//        });

        //Toast.makeText(Register3.this,Registration1.email+" "+Registration1.password+" "+Register2.firstname+" "+Register2.lastname
                //+" "+Register2.employeeid+" "+Register2.company,Toast.LENGTH_LONG).show();

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select Network");
        categories.add("MTN");
        categories.add("AIRTEL");
        categories.add("GLO");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);

                // get selected radio button from radioGroup
                int selectedId1 = radioGroup.getCheckedRadioButtonId();
                switch(selectedId1){
                    case R.id.radiochild11:
                        Constant.Time_adjusted = "Text";
//                        Toast.makeText(Register3.this,
//                                Constant.Time_adjusted, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.radiochild12:
                        Constant.Time_adjusted = "Email";
//                        Toast.makeText(Register3.this,
//                                Constant.Time_adjusted, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.radiochild13:
                        Constant.Time_adjusted = "Both";
//                        Toast.makeText(Register3.this,
//                                Constant.Time_adjusted, Toast.LENGTH_SHORT).show();
                        break;

                }


                RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);

                // get selected radio button from radioGroup
                int selectedId2 = radioGroup2.getCheckedRadioButtonId();
                switch(selectedId2){
                    case R.id.radiochild21:
                        Constant.Time_approve = "Text";
//                        Toast.makeText(Register3.this,
//                                Constant.Time_approve, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.radiochild22:
                        Constant.Time_approve = "Email";
//                        Toast.makeText(Register3.this,
//                                Constant.Time_approve, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.radiochild23:
                        Constant.Time_approve = "Both";
//                        Toast.makeText(Register3.this,
//                                Constant.Time_adjusted, Toast.LENGTH_SHORT).show();
                        break;

                }


                RadioGroup radioGroup3 = (RadioGroup) findViewById(R.id.radioGroup3);

                // get selected radio button from radioGroup
                int selectedId3 = radioGroup3.getCheckedRadioButtonId();
                switch(selectedId3){
                    case R.id.radiochild31:
                        Constant.Request_allowed = "Text";
//                        Toast.makeText(Register3.this,
//                                Constant.Request_allowed, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.radiochild32:
                        Constant.Request_allowed = "Email";
//                        Toast.makeText(Register3.this,
//                                Constant.Request_allowed, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.radiochild33:
                        Constant.Request_allowed = "Both";
//                        Toast.makeText(Register3.this,
//                                Constant.Request_allowed, Toast.LENGTH_SHORT).show();
                        break;

                }
                // find the radiobutton by returned id



//                // get selected radio button from radioGroup
//                int selectedId = radioGroup1.getCheckedRadioButtonId();
//
//                // find the radiobutton by returned id
//                RadioButton radioButton = (RadioButton) findViewById(selectedId);
//
//                Toast.makeText(Register3.this,
//                        radioButton.getText(), Toast.LENGTH_SHORT).show();



                checkedittext(mobile.getText().toString(),spinner.getSelectedItem().toString());
                if (isedittextok) {
                    //registerfunction(""+Register2.company, ""+Registration1.email, Register2.employeeid, ""+Registration1.password, "EIN4", "1705", ""+Register2.firstname, ""+Register2.firstname, false, mobile.getText().toString(), spinner.getSelectedItemPosition(), "text", "yes", "utgh");


                    registerfunction(Register2.company, Registration1.email, Register2.employeeid, Registration1.password, "EIN4", "1705", Register2.firstname, Register2.lastname, false,mobile.getText().toString(), spinner.getSelectedItemPosition(), "", "", "");

                }

            }

        });


        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             if (!isswitched){
                 notificationlin.setVisibility(LinearLayout.VISIBLE);
                 isswitched = true;
             }else{
                 notificationlin.setVisibility(LinearLayout.GONE);
                 isswitched = false;
             }

            }

        });



//        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                                                  @Override
//                                                  public void onCheckedChanged(RadioGroup group, int checkedId)
//                                                  {
//                                                                      // get selected radio button from radioGroup
//                int selectedId = radioGroup1.getCheckedRadioButtonId();
//
//                // find the radiobutton by returned id
//                RadioButton radioButton = (RadioButton) findViewById(selectedId);
//
//                Toast.makeText(Register3.this,
//                        radioButton.getText(), Toast.LENGTH_SHORT).show();
//                                                  }
//                                              }
//        );




    }


    public void checkedittext(String mobilenumber, String network){

        if(mobilenumber.isEmpty()) {
            Toast.makeText(Register3.this, "Mobile number is required", Toast.LENGTH_LONG).show();
            isedittextok = false;
            return;
        }

        if(network.equalsIgnoreCase("Select Network")){
            Toast.makeText(Register3.this, "Please select a network provider", Toast.LENGTH_LONG).show();
            isedittextok = false;
            return;
        }
        isedittextok = true;



    }


    public void registerfunction(String memberCode, String email, int pin, String password, String lookupType, String lookupValue, String firstName, String lastName, boolean isNewEmployee, String mobilePhoneNumber, int mobileCarrierId, String prefContactAdjustment, String prefContactApproval, String prefContactPto){


        dialog = new ProgressDialog(Register3.this);
        dialog.setMessage("Please wait...");
        dialog.show();
        ApiInterface api = RetrofitService.initializer();
        Registerrequest reg =  new Registerrequest(memberCode, email, pin, password, lookupType, lookupValue, firstName, lastName, isNewEmployee, mobilePhoneNumber, mobileCarrierId, prefContactAdjustment, prefContactApproval, prefContactPto);
        Call<Registerresp> call = api.registeruser(reg);
        call.enqueue(new Callback<Registerresp>() {
            @Override
            public void onResponse(Call<Registerresp> call, Response<Registerresp> response) {
                //Toast.makeText(Register3.this,response.code()+"",Toast.LENGTH_LONG).show();
            if(response.code() == 404) {
                dialog.dismiss();
                CustomDialogClassfail alert = new CustomDialogClassfail();
                alert.showDialog(Register3.this, "This user is not yet recognised!");
//                CustomDialogclasssuccess alert = new CustomDialogclasssuccess();
//                alert.showDialog(Register3.this, "Registration Succesfull!");
            }else if(response.code() == 200){

                CustomDialogclasssuccess alert = new CustomDialogclasssuccess();
                alert.showDialog(Register3.this, "Registration Succesfull!");


                if (response.isSuccessful()) {
                    Registerresp data = response.body();
                    dialog.dismiss();
                    toastmsg = data.getFirstName();
                    Log.w("response", toastmsg);
                }else{



                }

            }





            }

            @Override
            public void onFailure(Call<Registerresp> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(Register3.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
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
            case R.id.logout:
                break;

        }
        return true;
    }

}