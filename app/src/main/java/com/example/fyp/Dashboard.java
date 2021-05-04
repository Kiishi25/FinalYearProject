package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Dashboard extends Fragment {
RelativeLayout goals;
    RelativeLayout mood;
    RelativeLayout history;
    RelativeLayout profile;
    RelativeLayout journal;
    RelativeLayout moodHistory;
    RelativeLayout chat;
    RelativeLayout meditation;




    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dashboard, container, false);
        goals = (RelativeLayout) rootView.findViewById(R.id.goals);

        mood = (RelativeLayout) rootView.findViewById(R.id.mood);
        history = (RelativeLayout) rootView.findViewById(R.id.sleep);
        profile = (RelativeLayout) rootView.findViewById(R.id.profile);
        journal = (RelativeLayout) rootView.findViewById(R.id.journal);
        moodHistory = (RelativeLayout) rootView.findViewById(R.id.moodHistory);
        chat = (RelativeLayout) rootView.findViewById(R.id.chat);
        meditation = (RelativeLayout) rootView.findViewById(R.id.meditation);




        goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoalActivity.class);
                startActivity(intent);
              //  onBackPressed();
            }
        });
        meditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MeditationActivity.class);
                startActivity(intent);
                //  onBackPressed();
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Chatbot.class);
                startActivity(intent);
                //  onBackPressed();
            }
        });
        moodHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MoodHistoryActivity.class);
                startActivity(intent);
                //  onBackPressed();
            }
        });
        journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddJournalActivity.class);
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
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileViewActivity.class);
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
