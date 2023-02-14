package com.example.theapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class Flight extends AppCompatActivity {
    Button todiet;
    int minteger = 0, minteger2 = 0, minteger3 = 0, minteger4 = 0;
    int integerf, integerf2, integerf3, integerf4;
    HashMap<String , Object> mapflight = new HashMap<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);

        String userGname =  getIntent().getStringExtra("userGname");
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        todiet = (Button) findViewById(R.id.ToDiet);
        todiet.setOnClickListener(v -> {
            db.collection("users").document(userGname).set(mapflight, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(Flight.this,"Fly less, Nigga, or go Broke.",Toast.LENGTH_SHORT).show();
                }
            });

            Intent intent = new Intent(Flight.this,Diet.class);
            intent.putExtra("userGname", userGname);
            startActivity(intent);
        });
    }

    public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);

    }public void decreaseInteger(View view) {
        minteger = minteger - 1;
        if(minteger<0){
            minteger=0;
        }
        display(minteger);
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.integer_number);
        integerf = minteger;
        mapflight.put("Less than 3 hours", integerf);
        displayInteger.setText("" + number);
    }
    public void increaseInteger2(View view) {
        minteger2 = minteger2 + 1;
        display2(minteger2);

    }public void decreaseInteger2(View view) {
        minteger2 = minteger2 - 1;
        if(minteger2<0){
            minteger2=0;
        }
        display2(minteger2);
    }

    private void display2(int number) {
        TextView displayInteger2 = (TextView) findViewById(
                R.id.integer_number2);
        integerf2 = minteger2;
        mapflight.put("3 to 6 hours", integerf2);
        displayInteger2.setText("" + number);
    }
    public void increaseInteger3(View view) {
        minteger3 = minteger3 + 1;
        display3(minteger3);

    }public void decreaseInteger3(View view) {
        minteger3 = minteger3 - 1;
        if(minteger3<0){
            minteger3=0;
        }
        display3(minteger3);
    }

    private void display3(int number) {
        TextView displayInteger3 = (TextView) findViewById(
                R.id.integer_number3);
        integerf3 = minteger3;
        mapflight.put("6 to 9 hours", integerf3);
        displayInteger3.setText("" + number);
    }
    public void increaseInteger4(View view) {
        minteger4 = minteger4 + 1;
        display4(minteger4);

    }public void decreaseInteger4(View view) {
        minteger4 = minteger4 - 1;
        if(minteger4<0){
            minteger4=0;
        }
        display4(minteger4);
    }

    private void display4(int number) {
        TextView displayInteger4 = (TextView) findViewById(
                R.id.integer_number4);
        integerf4 = minteger4;
        mapflight.put("more than 9 hours", integerf4);
        displayInteger4.setText("" + number);
    }
}

