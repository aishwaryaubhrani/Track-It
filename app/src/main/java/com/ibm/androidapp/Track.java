package com.ibm.androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Track extends AppCompatActivity {
private Button display;
 ListView listview;
 FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
ArrayList<String> list = new ArrayList<>();

ConsignmentDetails cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        cd = new ConsignmentDetails();
        display= findViewById(R.id.display);
        SharedPreferences sharedPreferences = getSharedPreferences("PA",0);
        String trackingid1 = sharedPreferences.getString("trackingid", String.valueOf(1));
        display.setText("  Consignment ID : "+trackingid1);
        listview = findViewById(R.id.listview);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    cd = ds.getValue(ConsignmentDetails.class);
                    list.add("Client Name: "+cd.getClientName());
                    list.add("Client Number: "+cd.getClientNumber());
                    list.add("Delivery Address: "+cd.getDeliveryAddress());
                    list.add("Driver Name: "+cd.getDriverName());
                    list.add("Item Weight: "+cd.getItemWeight());
                    list.add("Vehicle Number: "+cd.getVehicleNumber());
                }
                listview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Track.this, MapsActivity.class));
            }
        });


    }
}