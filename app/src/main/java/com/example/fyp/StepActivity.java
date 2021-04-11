package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class StepActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepDetectorSensor;
    private Sensor accelerometer;
    private Sensor magnetometer;
    private float[] accelValues;
    private float[] magnetValues;

    //Variables used in calculations
    private int stepCount = 0;
    private long stepTimestamp = 0;
    private long startTime = 0;
    long timeInMilliseconds = 0;
    long elapsedTime = 0;
    long updatedTime = 0;
    private double distance = 0;
    private TextView dayRecordText;
    private TextView stepText;
    private TextView timeText;
    private TextView orientationText;
    private TextView distanceText;
    private TextView achievedText;
    private TextView speedText;

    private boolean active = false; //Used to checked if the counter is running
    private Handler handler = new Handler(); //Used to update the time in the UI

    //Preferences are used to remember the step record of the day
    private SharedPreferences sharedPreferences;
    private int dayStepRecord;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if (stepDetectorSensor == null) {
            //showErrorDialog();

            //sharedPreferences = PreferenceManager.getDefaultSharedPreferences());
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Get sensor values
        switch (event.sensor.getType()) {
            case (Sensor.TYPE_ACCELEROMETER):
                accelValues = event.values;
                break;
            case (Sensor.TYPE_MAGNETIC_FIELD):
                magnetValues = event.values;
                break;
            case (Sensor.TYPE_STEP_DETECTOR):
                countSteps(event.values[0]);
                break;

        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void countSteps(float step) {

        //Step count
        stepCount += (int) step;
        stepText.setText(String.format(getResources().getString(R.string.steps), stepCount));

        //Distance calculation
        distance = stepCount * 0.8; //Average step length in an average adult
        String distanceString = String.format("%.2f", distance);
        distanceText.setText(String.format(getResources().getString(R.string.distance), distanceString));

        //Record achievement
        if (stepCount >= dayStepRecord)
            achievedText.setVisibility(View.VISIBLE);
    }
    }

