package com.example.petskingdom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petskingdom.databinding.ActivityRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Register extends AppCompatActivity {
    //view binding
    private ActivityRegisterBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;

    private String username="", email="", password="", password_confirmation="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Creating your account...");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.registerBtn.setOnClickListener(view -> validateData());
        binding.clearBtn.setOnClickListener(view -> clear());
        binding.backBtn.setOnClickListener(view -> back());
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); //got ot previous activity when back button of actionbar clicked
        return super.onSupportNavigateUp();
    }

    private void validateData() {
        username = Objects.requireNonNull(binding.nameEt.getText()).toString().trim();
        email = Objects.requireNonNull(binding.emailEt.getText()).toString().trim();
        password = Objects.requireNonNull(binding.passwordEt.getText()).toString().trim();
        password_confirmation = Objects.requireNonNull(binding.cPasswordEt.getText()).toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailEt.setError("Invalid email format !!!");
        }
        else if(TextUtils.isEmpty(username)){
            binding.nameEt.setError("Enter Name !!!");
        }
        else if(TextUtils.isEmpty(password)){
            binding.passwordEt.setError("Enter password !!!");
        }
        else if(password.length()<8){
            binding.passwordEt.setError("Password must at least 8 characters long !!!");
        }
        else if(!password.equals(password_confirmation)){
            binding.cPasswordEt.setError("Confirmation Password must same as Password !!!");
        }
        else
        {
            firebaseSignUp();
        }
    }
    private void firebaseSignUp() {
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    progressDialog.dismiss();

                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    assert firebaseUser != null;
                    String email = firebaseUser.getEmail();

                    Toast.makeText(Register.this,"Account created\n"+email, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, MainActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(Register.this,""+e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
    public void clear(){
        Objects.requireNonNull(binding.emailEt.getText()).clear();
        Objects.requireNonNull(binding.passwordEt.getText()).clear();
    }
    public void back(){
        startActivity(new Intent(Register.this, Login.class));
        finish();
    }
}