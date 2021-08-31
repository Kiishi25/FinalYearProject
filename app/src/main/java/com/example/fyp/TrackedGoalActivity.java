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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.naz013.awcalendar.AwesomeCalendarView;
import com.github.naz013.awcalendar.Shape;
import com.github.naz013.awcalendar.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.events.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import hirondelle.date4j.DateTime;

import static com.example.fyp.R.id.goalsInfo;

public class TrackedGoalActivity extends AppCompatActivity {
    TextView user;
    Button button;
    Button submit;
    private String TAG = "Tracked";
    int value;
    String userId;
    int vs;
    Intent intent;

    String date;
    FirebaseUser rUser;
    FirebaseAuth fAuth;
    DatabaseReference databaseGoalInfo;
    ArrayList<TrackedGoals> list;
  //  ArrayList<String> l;
  String d;
    ArrayAdapter<TrackedGoals> adapter;
  //  ArrayAdapter<String> a;
     ListView listView;
     TrackedGoals g;
    String goalid;
    String type;
    TextView tt;
boolean unsorted = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracked_goal);

        // user = findViewById(R.id.user);
        button = findViewById(R.id.button);
        tt = findViewById(R.id.ty);

        //  Intent intent = getIntent();

        //  if (intent.getExtras() != null) {
        //      Goals goal = (Goals) intent.getSerializableExtra("data");

        //     user.setText(goal.getNames());
        //   }

        fAuth = FirebaseAuth.getInstance();
        rUser = fAuth.getCurrentUser();
        assert rUser != null;
        userId = rUser.getUid();


        //  adapter= new GoalsAdapter(getDataSetHistory(), GoalActivity.this);
        // Query query = databaseGoalInfo.child("GoalInfo");
        databaseGoalInfo = FirebaseDatabase.getInstance().getReference("TrackedGoals").child(userId);

        intent = getIntent();
        goalid = intent.getStringExtra("goalid");
        type = intent.getStringExtra("name");
        tt.setText(type);
        Log.i(TAG, "tt" + type);
        // query in the database to fetch appropriate data

        listView = findViewById(R.id.listView);
list = new ArrayList<>();
//l = new ArrayList<>();

//l.add("cool");
// get data from the table by the ListAdapter
    //   adapter = new ArrayAdapter<TrackedGoals>(this, R.layout.activity_tracked_goal, list);
        adapter = new ArrayAdapter<TrackedGoals>(this, R.layout.goals_info, goalsInfo,list);



        databaseGoalInfo.child(goalid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:  snapshot.getChildren()){
                     d = String.valueOf(ds.child("date").getValue());
                   vs = Integer.parseInt(ds.child("value").getValue().toString());
                  //  String vs = String.valueOf(ds.child("value").getValue());
                //    Log.i(TAG, "cool" + date + goalid + value);


                    g = new TrackedGoals(d, vs);
                   list.add(g);
                 //   Collections.reverse(list);
                    Log.i(TAG, "cool" + g);
                }
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        submit = findViewById(R.id.su);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText number = (EditText) findViewById(R.id.value);
                value = Integer.valueOf(number.getText().toString());
                EditText dates = (EditText) findViewById(R.id.date_in);
                date = dates.getText().toString();
                sendUserData();
             //   listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                number.setText("");
                dates.setText("");

             //   addItem(d,vs);


               // setContentView(R.layout.activity_tracked_goal);
            }
        });


    }
    public void addItem(String date, int value){
      //  g = new TrackedGoals(date, value);
  //      list.add(g);
//listView.setAdapter(adapter);
    }

    // Insert to database
    private void sendUserData() {


        // Collect information and key for new goal
        //  String goalId = databaseGoalInfo.push().getKey();
        // final String userId = rUser.getUid();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // String values = extras.getString("goalid");
            //  Log.i("Tracked Goal Acc", values);
            //The key argument here must match that used in the other activity
        }


        // TrackedGoals g = new TrackedGoals();


        // String goalId = databaseGoalInfo.child(fAuth.getUid()).push().getKey();
        String trackedgoalId = databaseGoalInfo.child(goalid).push().getKey();
        TrackedGoals goals = new TrackedGoals(date, value);
    //    Log.i(TAG, "Mood" + String.valueOf(goals.getDate() + goals.getValue()) + goalid);
/*
        databaseGoalInfo.child(goalid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:  snapshot.getChildren()){
                    String date = String.valueOf(ds.child("date").getValue());
                    String value = String.valueOf(ds.child("value").getValue());
                    Log.i(TAG, "cool" + date + goalid + value);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

 */
        // Submit
      //  Log.i(TAG, "goal" + goalid);
        databaseGoalInfo.child(goalid).child(trackedgoalId).setValue(goals);
        //  databaseGoalInfo.child(userid).setValue(goals);

      //  Toast.makeText(TrackedGoalActivity.this, "Database Updated", Toast.LENGTH_SHORT).show();
      //  listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }


}
