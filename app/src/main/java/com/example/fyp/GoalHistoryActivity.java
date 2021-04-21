package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoalHistoryActivity extends AppCompatActivity {
    private LineChart Temp_linechart;
    ArrayList<Entry> yData;
    ArrayList<String> name,stats,stats2;

    ValueEventListener valueEventListener;
    DatabaseReference databaseGoalInfo;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_history);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        stats2 = new ArrayList<String>();
        yData = new ArrayList<Entry>();
        barChart = (BarChart) findViewById(R.id.bar);

        databaseGoalInfo= FirebaseDatabase.getInstance().getReference("TrackedGoals");
        databaseGoalInfo.addValueEventListener(valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data1 : dataSnapshot.getChildren()) {
                    stats2.add(data1.child("value").getValue().toString());


                    //   ArrayList<BarEntry> entries = new ArrayList<>();

                    ArrayList<BarEntry> entries = new ArrayList<>();
                    for (int i = 0; i < stats2.size(); i++) {
                        String valspi = stats2.get(i);
                        entries.add(new BarEntry(Float.parseFloat(valspi), i));
                    }


                    //  entries.add(new BarEntry(8f, 0));
                    //  entries.add(new BarEntry(2f, 1));
                    //     entries.add(new BarEntry(5f, 2));
                    //  entries.add(new BarEntry(20f, 3));
                    //  entries.add(new BarEntry(15f, 4));
                    //  entries.add(new BarEntry(19f, 5));

                    BarDataSet bardataset = new BarDataSet(entries, "Cells");
                    final ArrayList<String> labels = new ArrayList<String>();
                    //  labels.clear();

                        String value = String.valueOf(data1.child("date").getValue());
                        Log.i("Value", value);
                        labels.add(value);


                    //      ArrayList<String> labels = new ArrayList<String>();
                    //     labels.add("2016");
                    //   labels.add("2015");
                    //     labels.add("2014");
                    // labels.add("2013");
                    // labels.add("2012");
                    // labels.add("2011");

                    BarData data = new BarData(labels, bardataset);
                    barChart.setData(data); // set the data and list of labels into chart
                    barChart.setDescription("Sleep Graph");  // set the description
                    bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                    barChart.animateY(5000);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

