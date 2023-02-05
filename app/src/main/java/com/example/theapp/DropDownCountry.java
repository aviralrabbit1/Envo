package com.example.theapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DropDownCountry extends AppCompatActivity {
    Button confirmcountry;
    FirebaseFirestore firestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_down_country);

        firestore = FirebaseFirestore.getInstance();

        Map<String,Object> users = new HashMap<>();
        users.put("first name","meow");
        users.put("Last name","meow");
        users.put("Description","nigga");
        firestore.collection("users").add(users).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"failure",Toast.LENGTH_LONG).show();

            }
        });

        String[] countryName = getResources().getStringArray(R.array.Country_Name);
        @SuppressLint("ResourceType") ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, countryName);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.RED);

        confirmcountry = (Button) findViewById(R.id.ConfirmCountry);
        confirmcountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHouseActivity();
            }
        });


    }
    public void openHouseActivity(){
        Intent intent = new Intent(this, DropdownHouse.class);
        startActivity(intent);
    }
}