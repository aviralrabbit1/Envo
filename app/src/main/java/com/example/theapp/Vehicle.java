package com.example.theapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.NumberFormat;
import java.util.HashMap;


public class Vehicle extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button confirmvehicle;
    String selectedTransport;
    String selectedKms;
    HashMap<String , Object> mapTransport = new HashMap<>();
    final String[] transportMeans = {"Public transport","Bike/Scooter","Petrol car","Diesel","Gas car","Electric"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);


        String userGname =  getIntent().getStringExtra("userGname");
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, transportMeans);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        confirmvehicle = (Button) findViewById(R.id.ConfirmVehicle);
        confirmvehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("users").document(userGname).set(mapTransport, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Vehicle.this,"Travel less, Nigga, or go Broke.",Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(Vehicle.this, Shopping.class);
                intent.putExtra("userGname", userGname);
                startActivity(intent);
            }
        });

        Slider slider = (Slider) findViewById(R.id.kmsSlider);
        TextView howManyKms = findViewById(R.id.howmanykms);

        slider.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                NumberFormat kms = NumberFormat.getNumberInstance();
                howManyKms.setText("Distance Travelled in one day: " + kms.format(value)+ " kms");
                selectedKms = kms.format(value);
                mapTransport.put("kms",selectedKms);
                return kms.format(value) + " kms";

            }
        });
    }
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), "Selected Type of Transport: "+transportMeans[position] ,Toast.LENGTH_LONG).show();
        selectedTransport = transportMeans[position];
        mapTransport.put("Mode of transport", selectedTransport);
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}