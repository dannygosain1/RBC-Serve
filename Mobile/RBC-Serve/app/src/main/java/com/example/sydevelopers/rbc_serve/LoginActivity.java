package com.example.sydevelopers.rbc_serve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText mUserName = (EditText) findViewById(R.id.user_name);
        EditText mPassword = (EditText) findViewById(R.id.user_password);

        final Button mSubmitButton = (Button) findViewById(R.id.submit);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(validateUser()){

                }
            }
        });
    }

    private boolean validateUser(){
        return true;
    }
}
