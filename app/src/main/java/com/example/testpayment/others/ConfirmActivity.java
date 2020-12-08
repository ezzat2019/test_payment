package com.example.testpayment.others;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testpayment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class ConfirmActivity extends AppCompatActivity {
    private EditText ed_code;
    Button btn_confirm;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private RegiterModel regiterModel;
    String number;
    String device_code;
    PhoneAuthProvider.ForceResendingToken forceResendingToken1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        ed_code = findViewById(R.id.ed_code);
        btn_confirm = findViewById(R.id.btn_confirm);


        if (getIntent().hasExtra("num")) {

            regiterModel = getIntent().getParcelableExtra("num");
            confirmNumber(regiterModel.getNum());
            Log.d("fffffffffff", "onFailure:8 " + regiterModel.getNum());
            btn_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verfy(ed_code.getText().toString().trim());
                }
            });
        } else {
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }


    }

    private void confirmNumber(String number) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber( "+2"+ number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(ConfirmActivity.this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                device_code = s;
                                forceResendingToken1 = forceResendingToken;
                                Log.d("aaaaa", "onCodeSent: "+s);


                            }

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                //    signInWithPhoneAuthCredential(phoneAuthCredential);
                                // verfy(phoneAuthCredential.getSmsCode());

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(getApplicationContext(), e.getMessage() + "", Toast.LENGTH_SHORT).show();
                                Log.d("fffffffffff", "onFailure:9 " + e.getMessage());
                                onBackPressed();

                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(options);
    }

    private void verfy(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(device_code, code);
        signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseDatabase.getInstance().getReference().child("users")
                                    .setValue(regiterModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);

                                }
                            });


                            // ...
                        } else {
                            Toast.makeText(ConfirmActivity.this, "error try again", Toast.LENGTH_SHORT).show();
                            onBackPressed();

                        }
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ConfirmActivity.this, e.getMessage() + "", Toast.LENGTH_SHORT).show();
                Log.d("fffffffffff", "onFailure:10 " + e.getMessage());
                onBackPressed();
            }
        });
    }
}