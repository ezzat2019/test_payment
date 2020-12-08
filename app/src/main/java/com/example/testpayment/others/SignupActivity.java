package com.example.testpayment.others;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.testpayment.R;

public class SignupActivity extends AppCompatActivity {
    Button btn_regit;
    EditText ed_regist_phone,ed_regist_pass,ed_regist_email;

private RegiterModel regiterModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();


        btn_regit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regiterModel=new RegiterModel(ed_regist_email.getText().toString()
                ,ed_regist_pass.getText().toString(),ed_regist_phone.getText().toString());
                Intent intent=new Intent(getApplicationContext(),ConfirmActivity.class);
                intent.putExtra("num",regiterModel);
                startActivity(intent);

            }
        });
    }





    private void init() {
        btn_regit=findViewById(R.id.btn_rgit_done);


        ed_regist_phone=findViewById(R.id.ed_regist_phone);
        ed_regist_pass=findViewById(R.id.ed_regist_pass);
        ed_regist_email=findViewById(R.id.ed_regist_email);

    }

    public void go(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}