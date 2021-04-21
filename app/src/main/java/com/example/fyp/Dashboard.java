package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Dashboard extends Fragment {
LinearLayout goals;
LinearLayout mood;
    LinearLayout history;



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dashboard, container, false);
        goals = (LinearLayout) rootView.findViewById(R.id.chat);

        mood = (LinearLayout) rootView.findViewById(R.id.mood);
        history = (LinearLayout) rootView.findViewById(R.id.sleep);





        goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoalActivity.class);
                startActivity(intent);
              //  onBackPressed();
            }
        });

        mood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                startActivity(intent);
             //   onBackPressed();
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoalHistoryActivity.class);
                startActivity(intent);

            }
        });
        return rootView;




    }



}
