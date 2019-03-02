package model;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.agonaika.data.localdb.LocalDbHelper;
import com.agonaika.data.localdb.dbobject.EmployeeDbo;

import api.ApiInterface;
import api.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Loginmodel{

    Loginhelper lhelper;

    Loginmodel(Loginhelper lh){

        this.lhelper = lh;

    }

    public void makeLogin(final Context context, String email, String password){




//        dialog = new ProgressDialog(Register3.this);
//        dialog.setMessage("Please wait...");
//        dialog.show();
            ApiInterface api = RetrofitService.initializer();
            Loginresquest login =  new Loginresquest("test","test");
            Call<Loginresponse> call = api.loginuser(login);
            call.enqueue(new Callback<Loginresponse>() {
                @Override
                public void onResponse(Call<Loginresponse> call, Response<Loginresponse> response) {
                    //Toast.makeText(Register3.this,response.code()+"",Toast.LENGTH_LONG).show();
                    if(response.code() == 404) {

                    }else if(response.code() == 200){



                        if (response.isSuccessful()) {
                            Loginresponse loginresp = response.body();
                            String Employeename = loginresp.getEmployeename();
                            String Phone = loginresp.getPhone();
                            String Membername = loginresp.getMember();
                            String Employeestatus = loginresp.getStatus();
                           String  Lastlogin = loginresp.getLast_login();


                            // Add a new student record
                            ContentValues values = new ContentValues();
                            values.put(EmployeeDbo.COL_BADGE_NUMBER,"test");

                            // Retrieve student records
                            String URL = "content://com.agonaika.LocalDbHelper";

                            Uri employeeURI = Uri.parse(URL);
                            // Uri uri = getContentResolver().insert(LocalDbHelper.CONTENT_URI, values);

                            LocalDbHelper n = new LocalDbHelper();
                            n.insert(employeeURI, values);

//                            Toast.makeText(context.getBaseContext(),
//                                    n.insert(employeeURI, values).toString(), Toast.LENGTH_LONG).show();

                            lhelper.succesful();


                        }else{



                        }

                    }





                }

                @Override
                public void onFailure(Call<Loginresponse> call, Throwable t) {


                }
            });



    }

}
