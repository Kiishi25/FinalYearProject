package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class addJournalData extends AppCompatActivity {

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
    //Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journal);
        fAuth = FirebaseAuth.getInstance();
        FirebaseUser rUser = fAuth.getCurrentUser();
        assert rUser != null;
        final String userId = rUser.getUid();
        myRef = FirebaseDatabase.getInstance().getReference("Journal").child(userId);

        //  create = findViewById(R.id.fabb);


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
                       // adapt.notifyDataSetChanged();
                    }
                });




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
                    startActivity(new Intent(getApplicationContext(),AddJournalActivity.class));

                    //setContentView(R.layout.journal);

                    // startActivity(new Intent(AddJournalActivity.this, MainActivity.class));
                }
                else {

                    Toast.makeText(addJournalData.this, "error!!",
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