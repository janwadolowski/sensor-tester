package com.example.sensortester;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor gyroscope;
    private Sensor gravity;
    private Sensor light;
    private Sensor linearAcceleration;
    private Sensor magneticField;
    private Sensor orientation;
    private Sensor pressure;
    private Sensor proximity;
    private Sensor rotationVector;
    private Sensor currentSensor;
    private TextView value;
    private TextView name;

//    private SensorEventListener sensorEventListener = new SensorEventListener() {
//        @Override
//        public void onSensorChanged(SensorEvent sensorEvent) {
//            StringBuilder stringBuilder = new StringBuilder();
//            char dim = 'X';
//            for (float value:sensorEvent.values) {
//                stringBuilder.append(String.valueOf(dim)).append(": ");
//                stringBuilder.append(String.valueOf(value));
//                stringBuilder.append("\n");
//                dim += 1;
//            }
//            sensorValue.setText(stringBuilder.toString());
//        }
//
//        @Override
//        public void onAccuracyChanged(Sensor sensor, int i) {
//
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Chip chipAccelerometer, chipMotionDetect, chipGravity, chipGyroscope, chipLight, chipLinearAcceleration, chipMagneticField, chipOrientation, chipPressure, chipProximity, chipRotationVector, chipWifi, chipBluetooth;

        chipAccelerometer = findViewById(R.id.accelerometer);
        chipGravity = findViewById(R.id.gravity);
        chipGyroscope = findViewById(R.id.gyroscope);
        chipLight = findViewById(R.id.light);
        chipLinearAcceleration = findViewById(R.id.linear_acceleration);
        chipMagneticField = findViewById(R.id.magnetic_field);
        chipOrientation = findViewById(R.id.orientation);
        chipPressure = findViewById(R.id.pressure);
        chipProximity = findViewById(R.id.proximity);
        chipRotationVector = findViewById(R.id.rotation_vector);
        chipWifi = findViewById(R.id.rotation_vector);
        chipBluetooth = findViewById(R.id.rotation_vector);

        value = (TextView) findViewById(R.id.sensorValue);
        name = (TextView) findViewById(R.id.sensorName);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        linearAcceleration = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        orientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        rotationVector = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        name.setText("Odczyt z sensora: " + accelerometer.getName());

        chipAccelerometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                currentSensor = accelerometer;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });

        chipGravity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                currentSensor = gravity;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        chipGyroscope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                currentSensor = gyroscope;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        chipLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                currentSensor = light;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        chipLinearAcceleration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                currentSensor = linearAcceleration;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        chipMagneticField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                currentSensor = magneticField;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        chipOrientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                currentSensor = orientation;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        chipPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                currentSensor = pressure;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        chipProximity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                currentSensor = proximity;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });

        chipRotationVector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                currentSensor = rotationVector;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor1 = sensorEvent.sensor;
        float[] values1 = sensorEvent.values;
        String output = "";
        String unit = "";
        List<Integer> accelerationValues = Arrays.asList(Sensor.TYPE_ACCELEROMETER, Sensor.TYPE_GRAVITY, Sensor.TYPE_LINEAR_ACCELERATION);
        if (sensor1.getType() == Sensor.TYPE_PROXIMITY) {
            unit = "cm";
            output = "%f %s";
            output = String.format(output, values1[0], unit);
        } else if (sensor1.getType() == Sensor.TYPE_LIGHT) {
            unit = "lx";
            output = "%f %s";
            output = String.format(output, values1[0], unit);
        } else if (sensor1.getType() == Sensor.TYPE_PRESSURE) {
            unit = "hPa";
            output = "%f %s";
            output = String.format(output, values1[0], unit);
        } else {
            output = "X: %f %s\n" +
                    "Y: %f %s\n" +
                    "Z: %f %s";
            if (accelerationValues.contains(sensor1.getType())) {
                unit = "m/s^2";
                output = String.format(output, values1[0], unit, values1[1], unit, values1[2], unit);
            } else if (sensor1.getType() == Sensor.TYPE_GYROSCOPE) {
                unit = "rad/s";
                output = String.format(output, values1[0], unit, values1[1], unit, values1[2], unit);
            } else if (sensor1.getType() == Sensor.TYPE_ROTATION_VECTOR) {
                output = String.format(output, values1[0], unit, values1[1], unit, values1[2], unit);
            } else if (sensor1.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                unit = "μT";
                output = String.format(output, values1[0], unit, values1[1], unit, values1[2], unit);
            } else if (sensor1.getType() == Sensor.TYPE_ORIENTATION) {
                unit = "°";
                output = String.format(output, values1[0], unit, values1[1], unit, values1[2], unit);
            } else {
                throw new UnsupportedOperationException();
            }
        }
        value.setText(output);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}