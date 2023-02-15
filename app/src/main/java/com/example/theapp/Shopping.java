package com.example.theapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Collections;
import java.util.HashMap;

public class Shopping extends AppCompatActivity {
    int clothingMinteger = 0, gadgetMinteger = 0;
    int clothingNumber, gadgetNumber;
    Button confirmhouse;
    HashMap<String , Object> mapShopping = new HashMap<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        String userGname =  getIntent().getStringExtra("userGname");
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        confirmhouse = (Button) findViewById(R.id.ConfirmHouse);
        confirmhouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("users").document(userGname).set(mapShopping, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Shopping.this,"Fly less, Nigga, or go Broke.",Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(Shopping.this, Result.class);
                intent.putExtra("userGname", userGname);
                startActivity(intent);
            }
        });
    }
    public void increaseInteger(View view) {
        clothingMinteger = clothingMinteger + 1;
        display(clothingMinteger);

    }public void decreaseInteger(View view) {
        clothingMinteger = clothingMinteger - 1;
        if(clothingMinteger<0){
            clothingMinteger=0;
        }
        display(clothingMinteger);
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.clothing_integer);
        clothingNumber = clothingMinteger;
        mapShopping.put("Clothes bought", clothingNumber);
        displayInteger.setText("" + number);
    }

    public void increaseInteger2(View view) {
        gadgetMinteger = gadgetMinteger + 1;
        display2(gadgetMinteger);

    }public void decreaseInteger2(View view) {
        gadgetMinteger = gadgetMinteger - 1;
        if(gadgetMinteger<0){
            gadgetMinteger=0;
        }
        display2(gadgetMinteger);
    }

    private void display2(int number) {
        TextView displayInteger2 = (TextView) findViewById(
                R.id.gadget_integer);
        gadgetNumber = gadgetMinteger;
        mapShopping.put("Gadget bought", gadgetNumber);
        displayInteger2.setText("" + number);
    }
}