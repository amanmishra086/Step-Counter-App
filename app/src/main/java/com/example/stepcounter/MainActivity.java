package com.example.stepcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView steps;
    Button stop;

    SensorManager sensorManager;

    boolean running=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        steps=findViewById(R.id.steps);
        stop=findViewById(R.id.stop);

       // sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);

   /*  stop.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

              // steps.setText("0");
               running=false;
         }
     });*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        running=true;
        Sensor countSensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
       if(countSensor!=null)
       {
           sensorManager.registerListener(this,countSensor,SensorManager.SENSOR_DELAY_UI);
       }else{
           Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();
       }
    }



    @Override
    protected void onPause() {
        super.onPause();
        running=false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        steps.setText(String.valueOf(event.values[0]));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
