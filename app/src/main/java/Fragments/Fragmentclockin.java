package Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.agonaika.agonaika.Clockin2;
import com.agonaika.agonaika.Dashboard;
import com.agonaika.agonaika.R;

public class Fragmentclockin extends Fragment {

    private CardView takebreak;
    private CardView clockout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clockin,container,false);
        takebreak = view.findViewById(R.id.card_view_take_break);
        clockout = view.findViewById(R.id.card_view_clockout);
        clockout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                ((Clockin2)getActivity()).setViewPager(1);

            }
        });

        takebreak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                ((Clockin2)getActivity()).setViewPager(2);

            }
        });
       

        return view;
    }

}
