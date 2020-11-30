package com.ibm.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
public class NumberToTrack extends AppCompatActivity {
private Button proceed;
private EditText entertrackingid;
private TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_to_track);
        proceed = findViewById(R.id.proceed);
        final Activity activity = this;
        entertrackingid = findViewById(R.id.entertrackingid);
        text1= findViewById(R.id.text1);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NumberToTrack.this,Track.class);
                startActivity(i);
                String trackingid = (entertrackingid.getText().toString());
                SharedPreferences sharedPreferences = getSharedPreferences("PA",0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("trackingid",trackingid);
                editor.commit();

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}