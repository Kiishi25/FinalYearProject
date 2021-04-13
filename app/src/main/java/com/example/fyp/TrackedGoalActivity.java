package com.example.fyp;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    FirebaseUser rUser;
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
        rUser = fAuth.getCurrentUser();
        assert rUser != null;


        //  adapter= new GoalsAdapter(getDataSetHistory(), GoalActivity.this);
        // Query query = databaseGoalInfo.child("GoalInfo");
        databaseGoalInfo = FirebaseDatabase.getInstance().getReference("TrackedGoals");



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
        String goalId = databaseGoalInfo.child(fAuth.getUid()).push().getKey();
        final String userId = rUser.getUid();
        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           // String values = extras.getString("goalid");
          //  Log.i("Tracked Goal Acc", values);
            //The key argument here must match that used in the other activity
        }

         String userid = intent.getStringExtra("userid");
       TrackedGoals g = new TrackedGoals();

        TrackedGoals goals = new TrackedGoals(userId,date, value);

        // String goalId = databaseGoalInfo.child(fAuth.getUid()).push().getKey();
        Log.i(TAG, "Mood" + String.valueOf(goals.getDate() + goals.getValue()));
        // Submit
        databaseGoalInfo.child(userid).setValue(goals);

        Toast.makeText(TrackedGoalActivity.this, "Database Updated", Toast.LENGTH_SHORT).show();
    }



}