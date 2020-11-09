package com.ibm.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NumberToTrack extends AppCompatActivity {
private Button proceed;
private EditText entertrackingid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_to_track);
        proceed = findViewById(R.id.proceed);
        entertrackingid = findViewById(R.id.entertrackingid);

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
}