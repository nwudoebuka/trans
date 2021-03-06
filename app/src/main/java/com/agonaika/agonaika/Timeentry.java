package com.agonaika.agonaika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import org.json.JSONArray;
import java.util.ArrayList;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import java.util.List;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import org.json.JSONException;
import org.json.JSONObject;

import api.DataAdapter2;

public class Timeentry extends AppCompatActivity {

    String[] dummyarr1 = {
            "Outsidein Africa",
            "Diamond Bank",
            "Kunatalogic",
            "App 24",
            "Newage ltd"
    };
    String[] dummyarr2 = {
            "Human Resource",
            "Teller",
            "App dev",
            "C.E.O",
            "App dev"
    };


    String[] dummyarr3 = {
            "Recruitment",
            "Deposits",
            "Android",
            "Management",
            "IOS"
    };

    List<DataAdapter2> ListOfdataAdapter;

    RecyclerView recyclerView;

    String HTTP_JSON_URL = "http://androidblog.esy.es/ImageJsonData.php";

    String Image_Name_JSON = "image_title";

    String Image_URL_JSON = "image_url";

    JsonArrayRequest RequestOfJSonArray ;

    RequestQueue requestQueue ;

    View view ;

    int RecyclerViewItemPosition ;

    RecyclerView.LayoutManager layoutManagerOfrecyclerView;

    RecyclerView.Adapter recyclerViewadapter;

    ArrayList<String> ImageTitleNameArrayListForClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeentry);


        ImageTitleNameArrayListForClick = new ArrayList<>();

        ListOfdataAdapter = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview2);

        recyclerView.setHasFixedSize(true);

        layoutManagerOfrecyclerView = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);

        //JSON_HTTP_CALL();

        Dummydata();

        // Implementing Click Listener on RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(Timeentry.this, new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                view = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(view != null && gestureDetector.onTouchEvent(motionEvent)) {

                    //Getting RecyclerView Clicked Item value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(view);
                    //startActivity(new Intent(Timesheet.this, Timeentry.class));
                    // Showing RecyclerView Clicked Item value using Toast.
                   //Toast.makeText(Timeentry.this, ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();

                    startActivity(new Intent(Timeentry.this, Daytime.class));

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


    }

    public void JSON_HTTP_CALL(){

        RequestOfJSonArray = new JsonArrayRequest(HTTP_JSON_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        ParseJSonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(Timeentry.this);

        requestQueue.add(RequestOfJSonArray);
    }

    public void ParseJSonResponse(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            DataAdapter2 GetDataAdapter2 = new DataAdapter2();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                GetDataAdapter2.setImageTitle(json.getString(Image_Name_JSON));

                // Adding image title name in array to display on RecyclerView click event.
                ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));

                GetDataAdapter2.setImageUrl(json.getString(Image_URL_JSON));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            ListOfdataAdapter.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapter2(ListOfdataAdapter, this);

        recyclerView.setAdapter(recyclerViewadapter);
    }


    public void Dummydata(){

        for(int i = 0; i<dummyarr1.length; i++) {


            DataAdapter2 Dummydetail = new DataAdapter2();
            Dummydetail.setImageTitle(dummyarr1[i]);
            Dummydetail.setImageTitle2(dummyarr2[i]);
            Dummydetail.setImageTitle3(dummyarr3[i]);
            //Toast.makeText(Timesheet.this, Dummydetail.getImageTitle(), Toast.LENGTH_LONG).show();

            // Adding image title name in array to display on RecyclerView click event.
            ImageTitleNameArrayListForClick.add(dummyarr1[i]);

            Dummydetail.setImageUrl("https://vignette.wikia.nocookie.net/bungostraydogs/images/1/1e/Profile-icon-9.png/revision/latest?cb=20171030104015");

            ListOfdataAdapter.add(Dummydetail);
        }

        recyclerViewadapter = new RecyclerViewAdapter2(ListOfdataAdapter, this);

        recyclerView.setAdapter(recyclerViewadapter);
    }
}