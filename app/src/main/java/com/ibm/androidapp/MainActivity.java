package com.ibm.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText enterPhoneNumber;
    private Button proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterPhoneNumber = findViewById(R.id.enterPhoneNumber);
        proceed = findViewById(R.id.proceed);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = enterPhoneNumber.getText().toString();
                if(phoneNum.isEmpty()){
                    enterPhoneNumber.setError("Required");
                    return;
                }

                if(!phoneNum.startsWith("+91")){
                    phoneNum = "+91"+phoneNum;
                }

                Intent intent = new Intent(MainActivity.this, OtpConfirmation.class);
                intent.putExtra("MOBILE_NUMBER", phoneNum);
                startActivity(intent);
                finish();

            }
        });
    }

}
