package Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agonaika.agonaika.Clockin2;
import com.agonaika.agonaika.R;

public class Fragmentclockout extends Fragment {
    private CardView clockin;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clock_out,container,false);
        clockin = view.findViewById(R.id.card_view_in);
        clockin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                ((Clockin2)getActivity()).setViewPager(0);

            }
        });
        return view;
    }
}
