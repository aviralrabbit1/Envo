package com.example.theapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class DropDownCountry extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Button confirmcountry;
    FirebaseFirestore firestore;
    String selectedCountry;
    HashMap<String, Object> mapCountry = new HashMap<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_down_country);

        String userGname =  getIntent().getStringExtra("userGname");
        FirebaseFirestore db = FirebaseFirestore.getInstance();



//        FirebaseDatabase.getInstance().getReference();

//        firestore = FirebaseFirestore.getInstance();

//        Map<String,Object> users = new HashMap<>();
//        users.put("first name","meow");
//        users.put("Last name","meow");
//        users.put("Description","nigga");
//        firestore.collection("users").add(users).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getApplicationContext(),"failure",Toast.LENGTH_LONG).show();
//
//            }
//        });

        String[] countryName = getResources().getStringArray(R.array.Country_Name);
        @SuppressLint("ResourceType")
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, countryName);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.RED);
        actv.setOnItemClickListener(this);


        confirmcountry = (Button) findViewById(R.id.ConfirmCountry);
        confirmcountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("users").document(userGname).set(mapCountry, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(DropDownCountry.this,"Poor ass bitch living in "+selectedCountry,Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(DropDownCountry.this, DropdownHouse.class);
                intent.putExtra("userGname", userGname);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        mapCountry.put("Country", item);

        // create Toast with user selected value
        Toast.makeText(DropDownCountry.this, "Selected Country is: \t" + item, Toast.LENGTH_SHORT).show();

    }
}