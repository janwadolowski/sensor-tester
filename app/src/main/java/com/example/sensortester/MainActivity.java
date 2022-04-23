package com.example.sensortester;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private BluetoothAdapter bluetoothAdapter;
    private SensorManager sensorManager;
    private WifiManager wifiManager;
    BroadcastReceiver wifiReceiver;
    BroadcastReceiver bluetoothReceiver;
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
    private List<ScanResult> wifis;
    private List<BluetoothDevice> bluetooths = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Chip chipAccelerometer, chipGravity, chipGyroscope, chipLight, chipLinearAcceleration, chipMagneticField, chipOrientation, chipPressure, chipProximity, chipRotationVector, chipWifi, chipBluetooth;

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
        chipWifi = findViewById(R.id.wifi);
        chipBluetooth = findViewById(R.id.bluetooth);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE}, 123);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT}, 124);
        }

        value = (TextView) findViewById(R.id.sensorValue);
        name = (TextView) findViewById(R.id.sensorName);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

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
        String dummy = "<oczekiwanie na pomiar>";

        wifiReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                wifis = wifiManager.getScanResults();
                unregisterReceiver(this);
            }
        };

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothAdapter.startDiscovery();
        bluetoothReceiver = new BroadcastReceiver() {
            @SuppressLint("MissingPermission")
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                // When discovery finds a device
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    bluetooths.add(device);
                } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                }
            }
        };

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(bluetoothReceiver, filter);

        chipAccelerometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                value.setText(dummy);
                currentSensor = accelerometer;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });

        chipGravity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                value.setText(dummy);
                currentSensor = gravity;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        chipGyroscope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                value.setText(dummy);
                currentSensor = gyroscope;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        chipLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                value.setText(dummy);
                currentSensor = light;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        chipLinearAcceleration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                value.setText(dummy);
                currentSensor = linearAcceleration;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        chipMagneticField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                value.setText(dummy);
                currentSensor = magneticField;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        chipOrientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                value.setText(dummy);
                currentSensor = orientation;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        chipPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                value.setText(dummy);
                currentSensor = pressure;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        chipProximity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                value.setText(dummy);
                currentSensor = proximity;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });

        chipRotationVector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                value.setText(dummy);
                currentSensor = rotationVector;
                name.setText("Odczyt z sensora: " + currentSensor.getName());
                sensorManager.registerListener(MainActivity.this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });

        chipWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                value.setText(dummy);
                name.setText("Odczyt z odbiornika wifi");
                List<ScanResult> wifis = wifiManager.getScanResults();
                StringBuilder sb = new StringBuilder();
                for (ScanResult result : wifis) {
                    sb.append("SSID: ").append(result.SSID).append("        RSSI: ").append(result.level).append("\n");
                }
                value.setText(sb.toString());
            }
        });

        chipBluetooth.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                sensorManager.unregisterListener(MainActivity.this);
                value.setText(dummy);
                name.setText("Odczyt z odbiornika bluetooth");

                StringBuilder sb = new StringBuilder();
                for (BluetoothDevice device : bluetooths) {

                    sb.append("nazwa: ").append(device.getName()).append("        adres: ").append(device.getAddress()).append("\n");
                }
                value.setText(sb.toString());
                if (bluetooths.isEmpty()) {
                    value.setText("Nie znalezniono dostępnych urządzeń bluetooth");
                }
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