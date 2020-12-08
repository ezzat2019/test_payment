package com.example.testpayment.others;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testpayment.R;

import java.io.Serializable;


public class MainActivity extends AppCompatActivity implements Serializable {
    Button btn_regit, btn_login;
    EditText ed_login_phone,ed_login_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        init();

        btn_regit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignupActivity.class));
            }
        });


    }

    private void init() {
        btn_regit=findViewById(R.id.btn_reg_login);
        btn_login=findViewById(R.id.btn_login);

        ed_login_phone=findViewById(R.id.ed_login_phone);
        ed_login_pass=findViewById(R.id.ed_login_pass);

    }


}
