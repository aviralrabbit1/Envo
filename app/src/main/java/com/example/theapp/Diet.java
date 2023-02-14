package com.example.theapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Collections;
import java.util.HashMap;

public class Diet extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int dietMinteger = 0;
    int dietInteger;
    HashMap<String , Object> mapDiet = new HashMap<>();
    String selectedDiet;
    Button confirmhouse;
    String[] dietOptions = { "Vegan", "Vegetarian", "Pescatarian", "Omnivore", "Carnivore" };


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        String userGname =  getIntent().getStringExtra("userGname");
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dietOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        confirmhouse = (Button) findViewById(R.id.ConfirmHouse);
        confirmhouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("users").document(userGname).set(mapDiet, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Diet.this,"Order less, Nigga, or go Broke.",Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(Diet.this, Vehicle.class);
                intent.putExtra("userGname", userGname);
                startActivity(intent);
            }
        });

    }
    public void increaseInteger(View view) {
        dietMinteger = dietMinteger + 1;
        display(dietMinteger);

    }public void decreaseInteger(View view) {
        dietMinteger = dietMinteger - 1;
        if(dietMinteger<1){
            dietMinteger=1;
        }
        display(dietMinteger);
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.integer_number);
        dietInteger = dietMinteger;
        mapDiet.put("Food deliveries per week", dietInteger);
        displayInteger.setText("" + number);
    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), "Selected Type of Diet: "+dietOptions[position] ,Toast.LENGTH_SHORT).show();
        selectedDiet = dietOptions[position];
        mapDiet.put("Type of diet",selectedDiet);
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}