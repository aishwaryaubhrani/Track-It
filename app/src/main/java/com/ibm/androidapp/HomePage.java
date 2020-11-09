package com.ibm.androidapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class HomePage extends AppCompatActivity {

    private EditText enterConsignmentNumber;
    private EditText enterClientName;
    private EditText enterClientNumber;
    private EditText enterDriverName;
    private EditText enterVehicleNumber;
    private EditText enterItemWeight;
    private EditText enterDeliveryAddress;
    private Button proceed;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        firebaseDatabase = FirebaseDatabase.getInstance();

        enterConsignmentNumber = findViewById(R.id.enterConsignmentNumber);
        enterClientName = findViewById(R.id.enterClientName);
        enterClientNumber = findViewById(R.id.enterClientPhoneNumber);
        enterDriverName = findViewById(R.id.enterDriverName);
        enterVehicleNumber = findViewById(R.id.enterVehicleNumber);
        enterItemWeight = findViewById(R.id.enterItemWeight);
        enterDeliveryAddress = findViewById(R.id.enterDeliveryLocation);
        proceed = findViewById(R.id.proceed);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String consignmentNumber = enterConsignmentNumber.getText().toString();
                String clientName = enterClientName.getText().toString();
                String clientNumber = enterClientNumber.getText().toString();
                String driverName = enterDriverName.getText().toString();
                String vehicleNumber = enterVehicleNumber.getText().toString();
                String itemWeight = enterItemWeight.getText().toString();
                String deliveryAddress = enterDeliveryAddress.getText().toString();

                if(consignmentNumber.isEmpty()){
                    enterConsignmentNumber.setError("Required");
                    return;
                }

                if(clientName.isEmpty()){
                    enterClientName.setError("Required");
                    return;
                }
                if(clientNumber.isEmpty()){
                    enterClientNumber.setError("Required");
                    return;
                }
                if(driverName.isEmpty()){
                    enterDriverName.setError("Required");
                    return;
                }
                if(vehicleNumber.isEmpty()){
                    enterVehicleNumber.setError("Required");
                    return;
                }
                if(itemWeight.isEmpty()){
                    enterItemWeight.setError("Required");
                    return;
                }
                if(deliveryAddress.isEmpty()){
                    enterDeliveryAddress.setError("Required");
                    return;
                }
                databaseReference = firebaseDatabase.getReference();
                ConsignmentDetails consignmentDetails = new ConsignmentDetails(clientName, clientNumber, driverName, vehicleNumber,
                        itemWeight, deliveryAddress);
                databaseReference.child(consignmentNumber).setValue(consignmentDetails);
            }
        });
    }
}