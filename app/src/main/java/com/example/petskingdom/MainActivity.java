package com.example.petskingdom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.petskingdom.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(navView, navController);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!= null){
            String name = account.getDisplayName();
            String email = account.getEmail();
            Uri profile = account.getPhotoUrl();
        }

        firebaseAuth = FirebaseAuth.getInstance();

        binding.btnLogout.setOnClickListener(view -> {
            firebaseAuth.signOut();
            Intent logoutIntent = new Intent(MainActivity.this, Login.class);
            startActivity(logoutIntent);
            finish();
        });
    }
}