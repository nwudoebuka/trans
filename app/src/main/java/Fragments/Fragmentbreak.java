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

public class Fragmentbreak extends Fragment {
    private CardView resumework;
    private CardView clockout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_on_break,container,false);
        clockout = view.findViewById(R.id.card_view_clockout);
        resumework = view.findViewById(R.id.card_view_resume);
        clockout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                ((Clockin2)getActivity()).setViewPager(1);

            }
        });
        resumework.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                ((Clockin2)getActivity()).setViewPager(0);

            }
        });
        return view;
    }
}
