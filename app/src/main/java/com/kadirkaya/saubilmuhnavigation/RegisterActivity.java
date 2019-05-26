package com.kadirkaya.saubilmuhnavigation;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private Toolbar actionbarRegister;
    private EditText txtUserName, txtEmailAddress, txtPassword;
    private Button btnRegister;
    private FirebaseAuth auth;

    public void init(){
        actionbarRegister = (Toolbar) findViewById(R.id.actionbarRegister);
        setSupportActionBar(actionbarRegister);
        getSupportActionBar().setTitle("Kayıt Ol");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();//Firebase'i aktif hale getirir.
        txtUserName = (EditText) findViewById(R.id.txtUsernameRegister);
        txtEmailAddress = (EditText) findViewById(R.id.txtEmailRegister);
        txtPassword = (EditText) findViewById(R.id.txtPasswordRegister);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewAccount();
            }
        });
    }
    private void CreateNewAccount(){
        String username = txtUserName.getText().toString();
        String email = txtEmailAddress.getText().toString();
        String password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email alanı boş olamaz.", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password alanı boş olamaz.", Toast.LENGTH_SHORT).show();
        }
        else{//Eğer uygunsa Firebase'de kaydı oluştur.
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Hesabınız başarılı bir şekilde oluşturuldu.", Toast.LENGTH_SHORT).show();
                        Intent loginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(loginActivity);
                        finish();
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Hesabınız oluşturulamadı. İnternet bağlantınızın olduğuna emin olun.", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }
}
