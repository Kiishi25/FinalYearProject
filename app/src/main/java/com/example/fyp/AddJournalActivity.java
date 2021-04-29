package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    EditText grateful;
    Toolbar toolbar;
    EditText reason;
     RecyclerView recyclerView;
    EditText today;
    EditText different;
    FirebaseAuth fAuth;
    private JournalAdapter adapter;
    DatabaseReference myRef;
    FloatingActionButton save;
    FloatingActionButton create;


    Calendar c;
    String todaysDate;
    String currentTime;
    private String key = "";
    private String task;
    private String description;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal);

        recyclerView = findViewById(R.id.rv);

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

        recyclerView.setLayoutManager(linearLayoutManager);
        myRef = FirebaseDatabase.getInstance().getReference("Journal").child(userId);

        FirebaseRecyclerOptions<Journal> options
                = new FirebaseRecyclerOptions.Builder<Journal>()
                .setQuery(myRef, Journal.class)
                .build();

        // Connecting object of required Adapter class to
        // the Adapter class itself


        adapter = new JournalAdapter(options);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        grateful = findViewById(R.id.grateful);
        reason = findViewById(R.id.reason);
        different = findViewById(R.id.different);
       today = findViewById(R.id.today);
        save = findViewById(R.id.save);
        create = findViewById(R.id.fabb);

      //  fAuth = FirebaseAuth.getInstance();
create.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        setContentView(R.layout.activity_add_journal);

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
                addJournal(grateful.getText().toString(), reason.getText().toString(), different.getText().toString(),today.getText().toString());
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

    private void addJournal(String grateful, String reason, String different, String today) {
        FirebaseUser rUser = fAuth.getCurrentUser();
        assert rUser != null;
        String userId = rUser.getUid();
        c = Calendar.getInstance();
        todaysDate = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);
        Log.d("DATE", "Date: "+todaysDate);
        currentTime = pad(c.get(Calendar.HOUR))+":"+pad(c.get(Calendar.MINUTE));
        Log.d("TIME", "Time: "+currentTime);
        myRef = FirebaseDatabase.getInstance().getReference("Journal").child(userId);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId",userId);
        hashMap.put("grateful",grateful);
        hashMap.put("reason", reason);
        hashMap.put("different", different);
        hashMap.put("date", todaysDate);



        myRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AddJournalActivity.this, "okay!!",
                            Toast.LENGTH_SHORT).show();

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