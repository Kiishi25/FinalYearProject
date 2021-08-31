package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GoalHistoryActivity extends AppCompatActivity {
    DatabaseReference trackedGoals;
    FirebaseAuth fAuth;
    int feeling;
    String date;
    TrackedGoals goal;

    // ValueEventListener valueEventListener;
    ArrayList<Integer> array2; //array for mood value
    ArrayList<String> array7; //array for date
    private String TAG = "History";
    BarChart barChart;
    private ArrayList<TrackedGoals> GoalsArray;
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        // mRatingBarCh = FirebaseDatabase.getInstance().getReference().child("Mood");
        /*
        fAuth = FirebaseAuth.getInstance();
        barChart = (BarChart) findViewById(R.id.lichart);
        GoalsArray = new ArrayList<TrackedGoals>();

        FirebaseUser rUser = fAuth.getCurrentUser();
        assert rUser != null;
        final String userId = rUser.getUid();


        trackedGoals =  FirebaseDatabase.getInstance().getReference("TrackedGoals").child(userId);
        trackedGoals.addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    String date = String.valueOf(ds.child("date").getValue());
                    int values = Integer.parseInt(ds.child("value").getValue().toString());
                    goal = new TrackedGoals(date, values);

                    Log.i(TAG, "Tracked" + date + values + userId) ;
                    GoalsArray.add(goal);







                }
                ArrayList<BarEntry> entries = new ArrayList<>();
                final ArrayList<String> labels = new ArrayList<String>();

                for (int i = 0; i < GoalsArray.size(); i++) {
                    String date = GoalsArray.get(i).getDate();
                    int value = GoalsArray.get(i).getValue();
                    entries.add(new BarEntry(value, i));
                    labels.add(date);
                    Log.i("label", String.valueOf(labels));
                    Log.i("Date", String.valueOf(date));
                    Log.i("Feel", String.valueOf(value));
                }
                BarDataSet bardataset = new BarDataSet(entries, "Cells");
                BarData data = new BarData(labels, bardataset);
                barChart.setData(data); // set the data and list of labels into chart
                barChart.setDescription("Mood Graph");  // set the description
                bardataset.setColors(new int[]{Color.RED, Color.GREEN, Color.GRAY, Color.BLACK, Color.BLUE});
                ;
                barChart.animateY(5000);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


            // String date = " ";
            //  mRatingBarCh= FirebaseDatabase.getInstance().getReference("Mood");


            // Query query = mRatingBarCh.child("Mood").orderByChild("date").startAt("2021-03-08").endAt("2021-03-14");
//Query query1 =  FirebaseDatabase.getInstance().getReference("Mood").orderByChild("date").startAt("2021-03-08").endAt("2021-03-14");
//query1.addListenerForSingleValueEvent(valueEventListener);
//System.out.println(query1 + "q");
            //  mRatingBarCh.orderByChild("date").startAt("2021-03-08").endAt("2021-03-14");


            //    query1= (Query) dataSnapshot.child("Feel").getValue();
            // String date = (String) dataSnapshot.child("date").getValue();
            //   System.out.println("mood" + feeling + "date" + query);
            //     ArrayList<BarEntry> entries = new ArrayList<>();

            //     entries.add(new BarEntry(8f, 0));
            //   entries.add(new BarEntry(2f, 1));
            //  entries.add(new BarEntry(5f, 2));
            //   entries.add(new BarEntry(20f, 3));
            //   entries.add(new BarEntry(15f, 4));


            //   final List<String> list = new ArrayList<>();
            //    BarDataSet bardataset = new BarDataSet(entries, "Cells");


            //      ArrayList<String> labels = new ArrayList<String>();
            //      for (DataSnapshot data : dataSnapshot.getChildren()){

            //        String mood = (String) dataSnapshot.child("Feel").getValue();
            //        labels.add(mood);


            //    }
            //    labels.add("Mon");
            //     labels.add("Tue");
            //      labels.add("Wed");
            //        labels.add("Thurs");
            //    labels.add("Fri");
            //     labels.add("Sat");


            //   BarData data = new BarData(labels, bardataset);
            //    barChart.setData(data); // set the data and list of labels into chart
            //     barChart.setDescription("Set Bar Chart Description Here");  // set the description
            //      bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
            //      barChart.animateY(5000);
            // date = dataSnapshot.child("moodScore").getValue().toString();
            //Toast.makeText(getApplicationContext(),date ,Toast.LENGTH_LONG).show();

        });


    }

         */


    }
}

