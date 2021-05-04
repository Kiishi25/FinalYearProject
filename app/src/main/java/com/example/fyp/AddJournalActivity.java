package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class AddJournalActivity extends AppCompatActivity {
    EditText grate;
    Toolbar toolbar;
    EditText reasons;
    private RecyclerView recycler;
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
        fAuth = FirebaseAuth.getInstance();
        FirebaseUser rUser = fAuth.getCurrentUser();
        assert rUser != null;
        final String userId = rUser.getUid();
        //   dialog=new DatePickerDialog();

        //    Query query = refpetrol.child("Data").orderByChild("timestamp").startAt(startmonth).endAt(endmonth);

        // goalId = databaseGoalInfo.child(firebaseAuth.getUid()).push().getKey();
        //   recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddJournalActivity.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        //  recyclerView.setHasFixedSize(true);

        recycler.setLayoutManager(linearLayoutManager);
        myRef = FirebaseDatabase.getInstance().getReference("Journal").child(userId);

        FirebaseRecyclerOptions<Journal> options
                = new FirebaseRecyclerOptions.Builder<Journal>()
                .setQuery(myRef, Journal.class)
                .build();

        // Connecting object of required Adapter class to
        // the Adapter class itself

        adapt = new JournalAdapter(options);
        recycler.setAdapter(adapt);
        recycler.setVisibility(View.VISIBLE);
        adapt.notifyDataSetChanged();


        //  fAuth = FirebaseAuth.getInstance();
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_add_journal);
                Button submit;
                submit = (Button) findViewById(R.id.savee);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        grate = (EditText) findViewById(R.id.grateful);
                        g = grate.getText().toString().trim();
                        reasons = (EditText) findViewById(R.id.reason);
                        r = reasons.getText().toString();
                        today = (EditText) findViewById(R.id.today);
                        t = today.getText().toString();
                        different = (EditText) findViewById(R.id.different);
                        d = different.getText().toString();






                        addJournal(g,r,d,t);
                    }
                });




            }

            });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case R.id.save:
//               addJournal(grate.getText().toString(), reasons.getText().toString(), different.getText().toString(),today.getText().toString());
                Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
                onBackPressed();
                break;


            case R.id.delete:
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
                onBackPressed();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    private void addJournal(String g, String r, String d, String t) {
        FirebaseUser rUser = fAuth.getCurrentUser();
        assert rUser != null;
        String userId = rUser.getUid();
        c = Calendar.getInstance();
        todaysDate = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);
        Log.d("DATE", "Date: "+todaysDate);
        currentTime = pad(c.get(Calendar.HOUR))+":"+pad(c.get(Calendar.MINUTE));
        Log.d("TIME", "Time: "+currentTime);
        String journalId = myRef.child(fAuth.getUid()).push().getKey();
        myRef = FirebaseDatabase.getInstance().getReference("Journal").child(userId).child(journalId);
        HashMap<String, String> hashMap = new HashMap<>();
       // String journalId = myRef.child(fAuth.getUid()).push().getKey();
        hashMap.put("journal",journalId);
        hashMap.put("userId",userId);
        hashMap.put("grateful",g);
        hashMap.put("reason", r);
        hashMap.put("different", d);
        hashMap.put("today", t);
        hashMap.put("date", todaysDate);



        myRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AddJournalActivity.this, "okay!!",
                            Toast.LENGTH_SHORT).show();
                    setContentView(R.layout.retrieved_layout_journal);

                   // startActivity(new Intent(AddJournalActivity.this, MainActivity.class));
                }
                else {

                    Toast.makeText(AddJournalActivity.this, "error!!",
                            Toast.LENGTH_SHORT).show();

                  //  progressBar.setVisibility(View.GONE);
                }
            }
        });



    }


    private String pad(int time) {
        if(time < 10)
            return "0"+time;
        return String.valueOf(time);

    }
}