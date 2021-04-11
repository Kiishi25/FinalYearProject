package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.naz013.awcalendar.AwesomeCalendarView;
import com.github.naz013.awcalendar.Shape;
import com.github.naz013.awcalendar.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.events.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import hirondelle.date4j.DateTime;

public class TrackedGoalActivity extends AppCompatActivity {

    TextView user;
    Button button;
    Button submit;
    private String TAG = "Tracked";
    int value;

    String date;
    FirebaseAuth fAuth;
    DatabaseReference databaseGoalInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracked_goal);

        // user = findViewById(R.id.user);
        button = findViewById(R.id.button);

      //  Intent intent = getIntent();

      //  if (intent.getExtras() != null) {
      //      Goals goal = (Goals) intent.getSerializableExtra("data");

       //     user.setText(goal.getNames());
     //   }

        fAuth = FirebaseAuth.getInstance();
        FirebaseUser rUser = fAuth.getCurrentUser();
        assert rUser != null;
        final String userId = rUser.getUid();

        //  adapter= new GoalsAdapter(getDataSetHistory(), GoalActivity.this);
        // Query query = databaseGoalInfo.child("GoalInfo");
        databaseGoalInfo = FirebaseDatabase.getInstance().getReference("GoalInfo").child(userId);

        // query in the database to fetch appropriate data




                submit = findViewById(R.id.su);
                submit.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        EditText number = (EditText) findViewById(R.id.value);
                        value = Integer.valueOf(number.getText().toString());
                        EditText dates = (EditText) findViewById(R.id.date_in);
                        date = dates.getText().toString();
                        sendUserData();
                        setContentView(R.layout.activity_tracked_goal);
                    }
                });

            }



    // Insert to database
    private void sendUserData(){



        // Collect information and key for new goal
        TrackedGoals goals = new TrackedGoals(date, value);

       // String goalId = databaseGoalInfo.child(fAuth.getUid()).push().getKey();
        Log.i(TAG, "Mood" + String.valueOf(goals.getDate() + goals.getValue()));
        // Submit
        databaseGoalInfo.child("TrackedGoals").setValue(goals);

        Toast.makeText(TrackedGoalActivity.this, "Database Updated", Toast.LENGTH_SHORT).show();
    }



}