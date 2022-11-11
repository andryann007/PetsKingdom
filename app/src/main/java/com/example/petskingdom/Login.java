package com.example.petskingdom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petskingdom.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Login extends AppCompatActivity {
    //view binding
    private ActivityLoginBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;

    private String email="", password="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Logging In");
        progressDialog.setCanceledOnTouchOutside(false);

        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();

        binding.loginBtn.setOnClickListener(view -> validateData());
        binding.forgotTv.setOnClickListener(view -> forget_password());
        binding.noAccountTv.setOnClickListener(view -> register());
        binding.clearBtn.setOnClickListener(view -> clear());
    }

    private void checkUser(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            startActivity(new Intent(Login.this,MainActivity.class));
            finish();
        }
    }

    private void validateData(){
        email = Objects.requireNonNull(binding.emailEt.getText()).toString().trim();
        password = Objects.requireNonNull(binding.passwordEt.getText()).toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailEt.setError("Invalid email format !!!");
        }
        else if(TextUtils.isEmpty(password)){
            binding.passwordEt.setError("Enter password !!!");
        }
        else
        {
            firebaseLogin();
        }
    }

    private void firebaseLogin(){
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    assert firebaseUser != null;
                    String email = firebaseUser.getEmail();

                    Toast.makeText(Login.this, "LoggedIn\n"+email, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(Login.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void register(){
        startActivity(new Intent(Login.this, Register.class));
        finish();
    }
    private void forget_password(){
        startActivity(new Intent(Login.this, ForgetPassword.class));
        finish();
    }
    public void clear(){
        Objects.requireNonNull(binding.emailEt.getText()).clear();
        Objects.requireNonNull(binding.passwordEt.getText()).clear();
    }
}