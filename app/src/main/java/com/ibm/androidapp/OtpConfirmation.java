package com.ibm.androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpConfirmation extends AppCompatActivity {

    private TextView mobileNumber;
    private EditText otp;
    private Button proceed;
    private static final String TAG = "OtpConfirmation";
    private String verificationID;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_confirmation);
        mobileNumber = findViewById(R.id.mobileNumber);
        otp = findViewById(R.id.enterOtp);
        proceed = findViewById(R.id.proceed);
        firebaseAuth = FirebaseAuth.getInstance();
        mobileNumber.setText(getIntent().getStringExtra("MOBILE_NUMBER"));
        sendVerificationCode(getIntent().getStringExtra("MOBILE_NUMBER"));
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOTP();
            }
        });
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack  = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String actualOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            Log.d(TAG, "CodeSent");
            super.onCodeSent(actualOtp, forceResendingToken);
            verificationID = actualOtp;
        }
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code != null){
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OtpConfirmation.this, "Verification Failed, Please Try Again!", Toast.LENGTH_SHORT).show();
        }

    };

    private void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(OtpConfirmation.this, Choice.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(OtpConfirmation.this, "Error Occurred! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendVerificationCode(String mobileNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(mobileNumber,
                120,
                TimeUnit.SECONDS,
                this,
                mCallBack
        );
    }

    public void checkOTP() {
        String code = otp.getText().toString();
        Log.d(TAG, code);
        if(code.isEmpty() || code.length() < 6){
            Toast.makeText(OtpConfirmation.this, "Please Enter the OTP.", Toast.LENGTH_SHORT).show();
            mobileNumber.requestFocus();
            return;
        }
        verifyCode(code);
    }
}