package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddJournalActivity extends AppCompatActivity {
    EditText grate;
    Toolbar toolbar;
    EditText reasons;
    RecyclerView recycler;
    EditText today;
    EditText different;
    FirebaseAuth fAuth;
    private JournalAdapter adapt;
    DatabaseReference myRef;
   // FloatingActionButton save;
    FloatingActionButton create;
    Button submit;
    String g;
    String d;
    String r;
    String t;


    Calendar c;
    String todaysDate;
    String currentTime;
    private String key = "";
    private String task;
    private String description;
DrawerLayout draw;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal);

        recycler = findViewById(R.id.recy);




       // save = findViewById(R.id.save);
     //   submit = (Button) findViewById(R.id.savee);
        create = findViewById(R.id.fabb);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(myToolbar);

        //   dialog=new DatePickerDialog();

        //    Query query = refpetrol.child("Data").orderByChild("timestamp").startAt(startmonth).endAt(endmonth);

        // goalId = databaseGoalInfo.child(firebaseAuth.getUid()).push().getKey();
        //   recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddJournalActivity.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        //  recyclerView.setHasFixedSize(true);


        recycler.setLayoutManager(linearLayoutManager);

        fAuth = FirebaseAuth.getInstance();
        FirebaseUser rUser = fAuth.getCurrentUser();
        assert rUser != null;
        final String userId = rUser.getUid();

        myRef = FirebaseDatabase.getInstance().getReference("Journal").child(userId);

        FirebaseRecyclerOptions<Journal> options
                = new FirebaseRecyclerOptions.Builder<Journal>()
                .setQuery(myRef, Journal.class)
                .build();
       // adapt.startListening();
        // Connecting object of required Adapter class to
        // the Adapter class itself

        adapt = new JournalAdapter(AddJournalActivity.this,options);
        recycler.setAdapter(adapt);
        recycler.setVisibility(View.VISIBLE);
        adapt.notifyDataSetChanged();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),addJournalData.class));
            }
        });
        adapt.notifyDataSetChanged();
     //   firebaseAuth = FirebaseAuth.getInstance();
      //  adapt.stopListening();
        //  fAuth = FirebaseAuth.getInstance();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button


        }
        return super.onOptionsItemSelected(item);
    }


    @Override protected void onStart()
    {
        super.onStart();
        adapt.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapt.stopListening();
    }

    private String pad(int time) {
        if(time < 10)
            return "0"+time;
        return String.valueOf(time);

    }

    public void onBackPressed() {

        super.onBackPressed();
    }

}