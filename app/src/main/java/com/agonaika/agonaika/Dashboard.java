package com.agonaika.agonaika;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.Toast;

import com.agonaika.agonaika.Adapters.CustomGridViewActivity;

public class Dashboard extends AppCompatActivity {
    GridView dashboardgrid;
    String[] gridViewString = {
            "Attendance", "Search Assignments", "Manage Expense", "Team", "Manage Leave", "Submit Photo",
            "Track Time", "Submit Report", "Info",

    } ;
    int[] gridViewImageId = {
            R.drawable.attendance_icon, R.drawable.search_icon, R.drawable.manage_expenses_icon, R.drawable.team_icon, R.drawable.manage_leave_icon, R.drawable.submit_photo_icon,
            R.drawable.tracktime_icon, R.drawable.submit_report, R.drawable.info_icon,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(Dashboard.this, gridViewString, gridViewImageId);
        dashboardgrid =(GridView)findViewById(R.id.grid_view_image_text);
        dashboardgrid.setAdapter(adapterViewAndroid);
        dashboardgrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                switch(gridViewString[+i]){
                    case "Track Time":
                        startActivity(new Intent(Dashboard.this, Clockin2.class));
                    break;

                }
                //Toast.makeText(Dashboard.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
            }
        });
    }
}
