package com.kadirkaya.saubilmuhnavigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {

    private Button btnWelcomeLogin, btnWelcomeRegister;
    public void init(){
        btnWelcomeLogin = (Button) findViewById(R.id.btnWelcomeLogin);
        btnWelcomeRegister = (Button) findViewById(R.id.btnWelcomeRegister);
    }

    private FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
        btnWelcomeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intentLogin);
            }
        });
        btnWelcomeRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
    }
}
