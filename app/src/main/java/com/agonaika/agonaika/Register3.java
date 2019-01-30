package com.agonaika.agonaika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import api.ApiInterface;
import api.RetrofitService;
import model.Registerrequest;
import model.Registerresp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        ApiInterface api = RetrofitService.initializer();
        Registerrequest reg =  new Registerrequest("","");
        Call<Registerresp> call = api.registeruser(reg);
        call.enqueue(new Callback<Registerresp>() {
            @Override
            public void onResponse(Call<Registerresp> call, Response<Registerresp> response) {

                Registerresp data = response.body();




            }

            @Override
            public void onFailure(Call<Registerresp> call, Throwable t) {

            }
        });
    }


}
