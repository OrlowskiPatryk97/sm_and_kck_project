package com.example.weudrowiec;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity2 extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor sensorlight;
    private SensorEventListener sensorEventListener;
    private View roottx;
    private float valuex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        roottx=findViewById(R.id.roottx);
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensorlight=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(sensorlight==null)
        {
            Toast.makeText(getApplicationContext(),"Nie ma sensoru światła",Toast.LENGTH_SHORT).show();
            finish();
        }
        valuex=sensorlight.getMaximumRange();
        sensorEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float value = event.values[0];
                if(value<400)
                {roottx.setBackgroundColor(Color.BLACK);
                    getSupportActionBar().setTitle("Jest ciemno na dworze zły czas na przechadzki");
                }
                else
                {roottx.setBackgroundColor(Color.WHITE);
                    getSupportActionBar().setTitle("Na dworze jest jasno dobry czas na spacerek" );
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener,sensorlight,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);

    }
}