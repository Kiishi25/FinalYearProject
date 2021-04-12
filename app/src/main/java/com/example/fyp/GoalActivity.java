package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.twigsntwines.daterangepicker.DatePickerSpinner;
import com.twigsntwines.daterangepicker.DateRangePickedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

import static java.lang.System.out;

public class GoalActivity extends AppCompatActivity {


    private SimpleDateFormat dateFormat;
    private Date date;


    private FirebaseAuth firebaseAuth;
    private RecyclerView recyclerView;
    DatabaseReference databaseGoalInfo;
    String goalName, period, days, type,measures;
    Integer num;
    Boolean orMore;
    // Spinner spinner;
    ViewGroup parent = null;
    LayoutInflater inflater = null;
    FloatingActionButton floatingActionButton;
    private String key = "";
    private String taskName;
    private List<Goals> goalsData;
    private RecyclerView rv;
    private GoalsAdapter adapter;
    String selectedDateStr;
    private static final String TAG = "GoalActivity";
    FirebaseAuth fAuth;
    String startDate;
    String endDate;
    Spinner measure;
    //we will use these constants later to pass the artist name and id to another activity
    public static final String GOAL_NAME = "come.example.Id";
  //  public static final String ARTIST_ID = "net.simplifiedcoding.firebasedatabaseexample.artistid";
    DatePickerSpinner spinners;
    // Button submit;
    Button button;

    //    String test;
    //Map<String, Boolean> checks = new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        //     inflater= (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        recyclerView = findViewById(R.id.recyclerView);


        //   recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GoalActivity.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        //  recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(linearLayoutManager);
        // Connecting Adapter class with the Recycler view*/

        // adapter.startListening();
        //spinners = (DatePickerSpinner) findViewById(R.id.datePickerSpinner); // Find Datepickerspinner declared in xml file
        //spinners.setDateRangePickedListener(new DateRangePickedListener() {
        //       @Override
        //      public void OnDateRangePicked(Calendar fromDate, Calendar toDate) {
        //         Log.e("From Date", String.valueOf(fromDate.getTime()));
        //         Log.e("To Date", String.valueOf(toDate.getTime()));
        //     }
        //    public void OnDatePickCancelled() {
        //        Log.e(TAG,"Date Pick Cancelled");
        //    }});

        fAuth = FirebaseAuth.getInstance();
        FirebaseUser rUser = fAuth.getCurrentUser();
        assert rUser != null;
        final String userId = rUser.getUid();

        //  adapter= new GoalsAdapter(getDataSetHistory(), GoalActivity.this);
        // Query query = databaseGoalInfo.child("GoalInfo");
        databaseGoalInfo = FirebaseDatabase.getInstance().getReference("GoalInfo").child(userId);
        databaseGoalInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data1 : snapshot.getChildren()){

                    String value = String.valueOf(data1.child("id").getValue());
                    Log.i("Value", value);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<Goals> options
                = new FirebaseRecyclerOptions.Builder<Goals>()
                .setQuery(databaseGoalInfo, Goals.class)
                .build();

        // Connecting object of required Adapter class to
        // the Adapter class itself


        adapter = new GoalsAdapter(options);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();

        //  parent = (ViewGroup) findViewById(R.id.mainxml);

//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//
        firebaseAuth = FirebaseAuth.getInstance();

        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   v = inflater.inflate(R.layout.input_file, null);
                setContentView(R.layout.input_file);
                Button submit;
                submit = (Button) findViewById(R.id.submit);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText nameGoalEditText = (EditText) findViewById(R.id.nameGoalEditText);
                        goalName = nameGoalEditText.getText().toString();
                        // Spinner spin = (Spinner) findViewById(R.id.categoriesSpinner);
                        //  type = spinner.getSelectedItem().toString();
                        //   System.out.println(type);
                        EditText startDateEdit = (EditText) findViewById(R.id.sd_in);
                        startDate = startDateEdit.getText().toString();
                        EditText endDateEdit = (EditText) findViewById(R.id.ed_in);
                        endDate = endDateEdit.getText().toString();
                        EditText number = (EditText) findViewById(R.id.num);
                        num = Integer.valueOf(number.getText().toString());
                        // Get goal period
                        ChipGroup chg = (ChipGroup) findViewById(R.id.goalPeriodChipGroup);
                        int chipId = chg.getCheckedChipId();
                        Chip periodChip = (Chip) findViewById(chg.getCheckedChipId());
                        period = periodChip.getText().toString();
                        Spinner spinner = (Spinner) findViewById(R.id.categoriesSpinner);

                        type  = spinner.getSelectedItem().toString();
                        Spinner spin = (Spinner) findViewById(R.id.measure);

                        measures  = spin.getSelectedItem().toString();

                        // Get goal
//        EditText numberEditText = (EditText) findViewById(R.id.goalNumberEditText);
                        //  num = Integer.parseInt(numberEditText.getText().toString());

                        // Get selected less/more radio button
                        ////     orMore = true;
                        ///    RadioGroup rgrp = (RadioGroup) findViewById(R.id.moreLessRadioGroup);
                        //    if (rgrp.getCheckedRadioButtonId() != R.id.moreRadioButton) {
                        //        orMore = false;
                        //     }

                        // Record selected days (bit string: i.e. SMTWTFS --> 0011000)
                        //  type = spinner.getSelectedItem().toString();
                        //type  = spinner.getSelectedItem().toString();
                        //  Toast.makeText(GoalActivity.this,type + " selected!", Toast.LENGTH_SHORT).show();

                        // Initialize the checks dict with the date
                        //   date = new Date();
                        //  dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                        //   String day = dateFormat.format(date);
                        //    checks.put(day, new Boolean(false)); //Set initially to false so they can check it off


                        // Add new goal
                        sendUserData();
                        //setContentView(R.layout.retrieved_layout);
                    }
                });


            }

        });

/*
        submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // Get goal name
        EditText nameGoalEditText = (EditText) findViewById(R.id.nameGoalEditText);
        goalName = nameGoalEditText.getText().toString();
        // Spinner spin = (Spinner) findViewById(R.id.categoriesSpinner);
        //  type = spinner.getSelectedItem().toString();
        //   System.out.println(type);
        EditText startDateEdit = (EditText) findViewById(R.id.sd_in);
        startDate = startDateEdit.getText().toString();
        EditText endDateEdit = (EditText) findViewById(R.id.ed_in);
        endDate = endDateEdit.getText().toString();
        EditText number = (EditText) findViewById(R.id.num);
        num = Integer.valueOf(number.getText().toString());
        // Get goal period
        ChipGroup chg = (ChipGroup) findViewById(R.id.goalPeriodChipGroup);
        int chipId = chg.getCheckedChipId();
        Chip periodChip = (Chip) findViewById(chg.getCheckedChipId());
        period = periodChip.getText().toString();
        // Get goal
//        EditText numberEditText = (EditText) findViewById(R.id.goalNumberEditText);
        //  num = Integer.parseInt(numberEditText.getText().toString());
        // Get selected less/more radio button
        ////     orMore = true;
        ///    RadioGroup rgrp = (RadioGroup) findViewById(R.id.moreLessRadioGroup);
        //    if (rgrp.getCheckedRadioButtonId() != R.id.moreRadioButton) {
        //        orMore = false;
        //     }
        // Record selected days (bit string: i.e. SMTWTFS --> 0011000)
        //  type = spinner.getSelectedItem().toString();
        //type  = spinner.getSelectedItem().toString();
        //  Toast.makeText(GoalActivity.this,type + " selected!", Toast.LENGTH_SHORT).show();
        // Initialize the checks dict with the date
        //   date = new Date();
        //  dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        //   String day = dateFormat.format(date);
        //    checks.put(day, new Boolean(false)); //Set initially to false so they can check it off
        // Add new goal
        sendUserData();
       // onSubmit(v);
    }
});
 */
    }

    /** Called when the user clicks the submit button */
/*
    public void onSubmit(View view) {
        // Get goal name
        EditText nameGoalEditText = (EditText) findViewById(R.id.nameGoalEditText);
        goalName = nameGoalEditText.getText().toString();
      // Spinner spin = (Spinner) findViewById(R.id.categoriesSpinner);
      //  type = spinner.getSelectedItem().toString();
     //   System.out.println(type);
        EditText startDateEdit = (EditText) findViewById(R.id.sd_in);
        startDate = startDateEdit.getText().toString();
        EditText endDateEdit = (EditText) findViewById(R.id.ed_in);
        endDate = endDateEdit.getText().toString();
        EditText number = (EditText) findViewById(R.id.num);
        num = Integer.valueOf(number.getText().toString());
        // Get goal period
        ChipGroup chg = (ChipGroup) findViewById(R.id.goalPeriodChipGroup);
        int chipId = chg.getCheckedChipId();
        Chip periodChip = (Chip) findViewById(chg.getCheckedChipId());
        period = periodChip.getText().toString();
        // Get goal
//        EditText numberEditText = (EditText) findViewById(R.id.goalNumberEditText);
      //  num = Integer.parseInt(numberEditText.getText().toString());
        // Get selected less/more radio button
   ////     orMore = true;
    ///    RadioGroup rgrp = (RadioGroup) findViewById(R.id.moreLessRadioGroup);
    //    if (rgrp.getCheckedRadioButtonId() != R.id.moreRadioButton) {
    //        orMore = false;
   //     }
        // Record selected days (bit string: i.e. SMTWTFS --> 0011000)
      //  type = spinner.getSelectedItem().toString();
        //type  = spinner.getSelectedItem().toString();
      //  Toast.makeText(GoalActivity.this,type + " selected!", Toast.LENGTH_SHORT).show();
        // Initialize the checks dict with the date
     //   date = new Date();
      //  dateFormat = new SimpleDateFormat("MM-dd-yyyy");
     //   String day = dateFormat.format(date);
    //    checks.put(day, new Boolean(false)); //Set initially to false so they can check it off
        // Add new goal
        sendUserData();
        // Redirect to main page
      //  Intent intent = new Intent(this, MainActivity.class);
    //    startActivity(intent);
    }
 */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            //  case R.id.save:

            //     onBackPressed();
            //     break;


            //    case R.id.delete:
            //      Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            //      onBackPressed();
            //      break;


        }
        return super.onOptionsItemSelected(item);
    }


    // Insert to database
    private void sendUserData(){

        // Collect information and key for new goal
        String goalId = databaseGoalInfo.child(firebaseAuth.getUid()).push().getKey();


        Goals goalInfo = new Goals(goalId, goalName, period, num, type,startDate,endDate, measures);
        Intent intent = new Intent (this, TrackedGoalActivity.class);
        intent.putExtra("userid,", goalId);
      //  String goalId = databaseGoalInfo.child(firebaseAuth.getUid()).push().getKey();

        // Submit
        databaseGoalInfo.child(goalId).setValue(goalInfo);
        Toast.makeText(GoalActivity.this, "Database Updated", Toast.LENGTH_SHORT).show();
    }


    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Log.d("onDataChange called", "data change");
    }


    public void onCancelled(@NonNull DatabaseError databaseError) {
        out.println("onCancelled called");

    }
    public void selectedGoal (Goals goal){
        startActivity (new Intent (GoalActivity.this,TrackedGoalActivity.class));
    }
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }



}