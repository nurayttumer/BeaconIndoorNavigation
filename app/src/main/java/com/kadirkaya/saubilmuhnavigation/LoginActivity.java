package com.kadirkaya.saubilmuhnavigation;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private Toolbar actionbarLogin;
    private EditText txtEmail, txtPassword;
    private Button btnLogin;
    FirebaseAuth auth;

    public void init(){
        actionbarLogin = (Toolbar) findViewById(R.id.actionbarLogin);
        setSupportActionBar(actionbarLogin);
        getSupportActionBar().setTitle("Giriş Yap");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtEmail = (EditText) findViewById(R.id.txtEmailLogin);
        txtPassword = (EditText) findViewById(R.id.txtPasswordLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser(){
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email alanı boş olamaz.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password alanı boş olamaz.", Toast.LENGTH_SHORT).show();
        }else{
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Giriş başarılı.", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "İnternet bağlantınız açık ve giriş bilgileriniz doğru olmalıdır.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

}
