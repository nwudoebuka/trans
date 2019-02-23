package com.agonaika.agonaika;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import Fragments.Fragmentbreak;
import Fragments.Fragmentclockin;
import Fragments.Fragmentclockout;
import api.SectionsStatePagerAdapter;

public class Clockin2 extends AppCompatActivity {

    private SectionsStatePagerAdapter sectionspagead;
    private ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockin2);

        mViewpager = findViewById(R.id.frag_holder);
        setupviewpager(mViewpager);

        setTitle("Clock in");
        if(getActionBar()!= null){
            getActionBar().setDisplayHomeAsUpEnabled(true);

        }

        mViewpager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });
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
//            case R.id.logout:
//                break;

        }
        return true;
    }


    private void setupviewpager(ViewPager viewPager){

        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragmentclockin(),"Clock In");
        adapter.addFragment(new Fragmentclockout(),"Clock Out");
        adapter.addFragment(new Fragmentbreak(),"Break");

        viewPager.setAdapter(adapter);

    }
public void setViewPager(int fragmentnumber){

        mViewpager.setCurrentItem(fragmentnumber);
}

}
