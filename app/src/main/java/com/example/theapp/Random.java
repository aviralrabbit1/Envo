package com.example.theapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;

public class Random extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);


//        putting house value in map
//        map.put("Residence", actv);
//        FirebaseDatabase.getInstance().getReference().child("Individual").child("users").child("Residence").updateChildren(map);

    }
//                FirebaseDatabase.getInstance().getReference().child("Individual").child("users").setValue(gName);

}