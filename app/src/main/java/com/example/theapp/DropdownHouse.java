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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Collections;
import java.util.HashMap;

public class DropdownHouse extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int minteger = 0;
    int members;
    Button confirmhouse;
    String HouseResidence;
    String[] residence = { "I'm broke af (shared room)", "Studio", "1BHK", "2BHK", "3BHK","I'm super rich" };

    HashMap<String , Object> mapHouse = new HashMap<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dropdown_house);

        String userGname =  getIntent().getStringExtra("userGname");
        Toast.makeText(DropdownHouse.this,"Welcome "+ userGname,Toast.LENGTH_SHORT).show();

//        DocumentReference ref = FirebaseFirestore.getInstance().collection("users").document(userGname);
//        ref.update("residence",HouseResidence);
//        ref.update("Members", members);
//
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, residence);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        confirmhouse = (Button) findViewById(R.id.ConfirmHouse);
        confirmhouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("users").document(userGname).set(mapHouse, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(DropdownHouse.this,"Lives in "+ HouseResidence + " with "+ members + " members",Toast.LENGTH_SHORT).show();
                    }
                });

                Intent intent = new Intent(DropdownHouse.this, Flight.class);

//                String userGName = userGname.toString();
                intent.putExtra("userGname", userGname.toString());
//                mapHouse.put(HouseResidence,members);
//                FirebaseDatabase.getInstance().getReference().child("Individual").child("users").child("Residence").setValue(mapHouse);
                startActivity(intent);
            }
        });

    }
    public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);

    }public void decreaseInteger(View view) {
        minteger = minteger - 1;
        if(minteger<1){
            minteger=1;
        }
        display(minteger);
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.integer_numberHouse);
        members=minteger;
        mapHouse.put("Members", members);
        displayInteger.setText("" + number);
    }
    
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), "Selected Type of Residence: "+residence[position] ,Toast.LENGTH_LONG).show();
        HouseResidence = residence[position];
        mapHouse.put("residence",HouseResidence);
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}